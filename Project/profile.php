<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manga Profile</title>

<style>
body {
    background: #2b2b2b;
    font-family: Arial, sans-serif;
}

.container {
    width: 900px;
    margin: 50px auto;
    background: linear-gradient(to right, #0a0a0a, #2a0000);
    padding: 20px;
    border-radius: 10px;
    border: 1px solid #555;
}

.title {
    color: red;
    font-weight: bold;
    margin-bottom: 20px;
}

.grid {
    display: grid;
    grid-template-columns: 1fr 2fr;
    gap: 20px;
}

.card {
    background: #111;
    border-radius: 10px;
    padding: 20px;
    color: white;
}

.right-top {
    height: 120px;
}

.right-bottom {
    border: 2px solid #00aaff;
}

.avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background: gray;
    margin-bottom: 10px;
}

.btn {
    background: red;
    color: white;
    padding: 8px 15px;
    border: none;
    margin-top: 10px;
    cursor: pointer;
}

.stats {
    display: flex;
    justify-content: space-around;
    margin-top: 10px;
}
</style>

</head>
<body>

<?php
// SAMPLE USER DATA
$username = "Aquilla";
$memberSince = "Jan 2026";
$favGenre = "Action / Romance";

// READING HISTORY
$history = [
    "Demon Slayer - Chapter 120",
    "Attack on Titan - Chapter 80",
    "Jujutsu Kaisen - Chapter 200"
];

// STATS
$totalRead = 120;
$favorites = 25;
$chaptersRead = 560;
?>

<div class="container">
    <h2 class="title">PROFILE</h2>

    <div class="grid">
        
        <!-- LEFT CARD -->
        <div class="card">
            <div class="avatar"></div>
            <h2><?php echo $username; ?></h2>
            <p>Member Since: <?php echo $memberSince; ?></p>
            <p>Favorite Genre: <?php echo $favGenre; ?></p>

            <button class="btn">EDIT PROFILE</button>
        </div>

        <!-- RIGHT SIDE -->
        <div>
            
            <!-- TOP RIGHT (READING HISTORY) -->
            <div class="card right-top">
                <h3>Reading History</h3>
                <ul>
                    <?php foreach($history as $manga){ ?>
                        <li><?php echo $manga; ?></li>
                    <?php } ?>
                </ul>
            </div>

            <!-- BOTTOM RIGHT (STATS) -->
            <div class="card right-bottom">
                <h3>Manga Stats</h3>

                <div class="stats">
                    <div>
                        <h2><?php echo $totalRead; ?></h2>
                        <p>Manga Read</p>
                    </div>

                    <div>
                        <h2><?php echo $favorites; ?></h2>
                        <p>Favorites</p>
                    </div>

                    <div>
                        <h2><?php echo $chaptersRead; ?></h2>
                        <p>Chapters</p>
                    </div>
                </div>

            </div>

        </div>

    </div>
</div>

</body>
</html>