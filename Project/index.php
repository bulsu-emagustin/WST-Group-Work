<?php
require 'config.php';
session_start();

if (!isset($_SESSION["login_data"])) {
    session_destroy();
    header('Location: login.php');
    exit();
}

$user_data = $_SESSION["login_data"];
$userid = $user_data["id"];

try {
    require 'db/action/dbconfig.php';

    $stmt = $conn->prepare("SELECT * FROM login WHERE id = ?");
    $stmt->execute([$userid]);
    $user_data = $stmt->fetch(PDO::FETCH_ASSOC);

} catch (PDOException $e) {
    die("DB Error: " . $e->getMessage());
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MangaQuilla | Home</title>

    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Barlow:wght@400;500;600;700&family=Barlow+Condensed:wght@700;800&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        :root {
            --bg:       #111111;
            --surface:  #1a1a1a;
            --surface2: #222222;
            --border:   #2e2e2e;
            --red:      #e02020;
            --red-dark: #b81818;
            --text:     #f0f0f0;
            --muted:    #888888;
            --gold:     #f5a623;
        }

        body {
            background: var(--bg);
            color: var(--text);
            font-family: 'Barlow', sans-serif;
            min-height: 100vh;
        }

        /* ===== TOP HEADER ===== */
        .top-header {
            background: #0d0d0d;
            border-bottom: 3px solid var(--red);
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 1.3rem 2.5rem;
            gap: 1rem;
        }

        .logo {
            font-family: 'Barlow Condensed', sans-serif;
            font-size: 2rem;
            font-weight: 800;
            letter-spacing: 0.03em;
            color: #fff;
            text-decoration: none;
        }

        .logo span { color: var(--red); }

        .search-bar {
            flex: 1;
            max-width: 520px;
            position: relative;
        }

        .search-bar input {
            width: 100%;
            background: #1c1c1c;
            border: 1px solid var(--border);
            color: var(--text);
            padding: 0.85rem 3rem 0.85rem 1rem;
            border-radius: 8px;
            font-size: 0.95rem;
            font-family: 'Barlow', sans-serif;
        }

        .search-bar input:focus {
            outline: none;
            border-color: var(--red);
        }

        .search-bar .icon {
            position: absolute;
            right: 1rem;
            top: 50%;
            transform: translateY(-50%);
            color: var(--muted);
            font-size: 1.1rem;
            pointer-events: none;
        }

        .profile-circle {
            width: 46px;
            height: 46px;
            border-radius: 50%;
            border: 2px solid var(--red);
            object-fit: cover;
            flex-shrink: 0;
        }

        /* ===== NAVIGATION ===== */
        nav {
            background: #0d0d0d;
            border-bottom: 1px solid var(--border);
            display: flex;
            justify-content: center;
            gap: 0;
        }

        nav a {
            text-decoration: none;
            color: var(--muted);
            font-family: 'Barlow Condensed', sans-serif;
            font-weight: 700;
            font-size: 0.9rem;
            letter-spacing: 0.12em;
            padding: 1.1rem 2.4rem;
            text-transform: uppercase;
            position: relative;
            transition: color 0.2s;
        }

        nav a:hover { color: var(--text); }

        nav a.active { color: var(--text); }

        nav a.active::after {
            content: '';
            position: absolute;
            bottom: 0; left: 50%;
            transform: translateX(-50%);
            width: 60%;
            height: 2px;
            background: var(--red);
            border-radius: 2px;
        }

        /* ===== HERO SECTION ===== */
        .hero-banner {
            height: 450px;
            background: linear-gradient(rgba(0,0,0,0.35), var(--bg)),
                        url('images/demonslayer.jpg') center/cover no-repeat;
            display: flex;
            align-items: center;
            padding: 0 10%;
        }

        .hero-title {
            font-family: 'Bebas Neue', cursive;
            font-size: 4.5rem;
            line-height: 0.9;
            color: #fff;
        }

        .hero-title span { color: var(--red); }

        .hero-sub {
            color: var(--muted);
            font-size: 0.95rem;
            max-width: 480px;
            margin-top: 1rem;
            line-height: 1.6;
        }

        /* ===== PRODUCTS SECTION ===== */
        .products-section {
            max-width: 1250px;
            margin: 3rem auto 4rem;
            padding: 0 2.5rem;
        }

        .section-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 1rem;
            margin-bottom: 1.8rem;
            flex-wrap: wrap;
        }

        .section-title {
            display: flex;
            align-items: center;
            gap: 0.85rem;
        }

        .section-title .line {
            width: 4px;
            height: 34px;
            background: var(--red);
            border-radius: 2px;
        }

        .section-title h2 {
            font-family: 'Barlow Condensed', sans-serif;
            font-size: 2.2rem;
            letter-spacing: 0.05em;
            color: #fff;
            text-transform: uppercase;
        }

        /* ===== PRODUCT GRID ===== */
        .product-grid {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 1.2rem;
        }

        .product-card {
            background: #161616;
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 1rem;
            transition: transform 0.2s, border-color 0.2s, box-shadow 0.2s;
            display: flex;
            flex-direction: column;
            text-decoration: none;
            color: inherit;
        }

        .product-card:hover {
            transform: translateY(-4px);
            border-color: var(--red);
            box-shadow: 0 10px 28px rgba(0,0,0,0.35);
            color: inherit;
        }

        .product-image {
            width: 100%;
            aspect-ratio: 3/4;
            border-radius: 5px;
            overflow: hidden;
            background: var(--surface2);
            margin-bottom: 0.9rem;
            border: 1px solid #252525;
        }

        .product-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
            transition: transform 0.3s ease;
        }

        .product-card:hover .product-image img {
            transform: scale(1.03);
        }

        .product-title {
            font-family: 'Barlow Condensed', sans-serif;
            font-size: 1.05rem;
            font-weight: 700;
            color: #f4f4f4;
            line-height: 1.35;
            min-height: 2.4rem;
            margin-bottom: 0.5rem;
        }

        .product-excerpt {
            font-size: 0.8rem;
            color: var(--muted);
            line-height: 1.5;
            margin-bottom: 0.9rem;
            flex: 1;
        }

        .btn-update {
            margin-top: auto;
            width: 100%;
            padding: 0.85rem 1rem;
            font-family: 'Barlow Condensed', sans-serif;
            font-size: 0.85rem;
            font-weight: 700;
            letter-spacing: 0.12em;
            text-transform: uppercase;
            border-radius: 5px;
            border: none;
            background: var(--red);
            color: #fff;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            display: block;
            transition: all 0.2s;
        }

        .btn-update:hover {
            background: var(--red-dark);
            transform: translateY(-1px);
            box-shadow: 0 6px 18px rgba(224,32,32,0.35);
            color: #fff;
        }

        /* ===== FOOTER ===== */
        footer {
            text-align: center;
            padding: 2rem;
            border-top: 1px solid var(--border);
            color: var(--muted);
            font-size: 0.8rem;
            font-family: 'Barlow Condensed', sans-serif;
            letter-spacing: 0.08em;
            text-transform: uppercase;
        }

        /* ===== RESPONSIVE ===== */
        @media (max-width: 1150px) {
            .product-grid { grid-template-columns: repeat(3, 1fr); }
        }

        @media (max-width: 820px) {
            .top-header { flex-wrap: wrap; justify-content: center; }
            .search-bar { order: 3; width: 100%; max-width: 100%; }
            .product-grid { grid-template-columns: repeat(2, 1fr); }
        }

        @media (max-width: 560px) {
            .products-section { padding: 0 1.2rem; }
            nav { flex-wrap: wrap; }
            nav a { padding: 1rem 1.2rem; }
            .product-grid { grid-template-columns: 1fr; }
            .section-title h2 { font-size: 1.8rem; }
            .hero-title { font-size: 3rem; }
        }
    </style>
</head>
<body>

<!-- HEADER -->
<header class="top-header">
    <a href="#" class="logo">MANGA<span>QUILLA</span></a>

    <div class="search-bar">
        <input type="text" placeholder="Search your favorite manga...">
        <span class="icon">🔍</span>
    </div>

    <img src="<?php echo htmlspecialchars($user_data['img_url']); ?>" class="profile-circle" alt="Profile">
</header>

<!-- NAV -->
<nav>
    <a href="#" class="active">Home</a>
    <a href="product1.php">Products</a>
    <a href="#">New Arrivals</a>
    <a href="profile.php">My Profile</a>
</nav>

<!-- HERO -->
<div class="hero-banner">
    <div>
        <h1 class="hero-title">DEMON SLAYER:<br><span>KIMETSU NO YAIBA</span></h1>
        <p class="hero-sub">
            Join Tanjiro Kamado in his quest to save his sister and hunt down the demons that slaughtered his family.
        </p>
    </div>
</div>

<!-- PRODUCTS -->
<section class="products-section">

    <div class="section-header">
        <div class="section-title">
            <div class="line"></div>
            <h2>All Titles</h2>
        </div>
    </div>

    <div class="product-grid">
        <?php
        $books = $conn->query("SELECT * FROM books")->fetchAll(PDO::FETCH_ASSOC);
        foreach ($books as $row) :
        ?>
        <div class="product-card">
            <div class="product-image">
                <img src="<?php echo htmlspecialchars($row['image']); ?>" alt="<?php echo htmlspecialchars($row['title']); ?>">
            </div>
            <div class="product-title"><?php echo htmlspecialchars($row['title']); ?></div>
            <p class="product-excerpt"><?php echo substr(htmlspecialchars($row['excerpt']), 0, 80); ?>...</p>
            <a href="updatebooks.php?id=<?php echo $row['id']; ?>" class="btn-update">Update Title</a>
        </div>
        <?php endforeach; ?>
    </div>

</section>

<!-- FOOTER -->
<footer>
    &copy; 2026 MANGAQUILLA &bull; CICT Bulacan State University
</footer>

</body>
</html>