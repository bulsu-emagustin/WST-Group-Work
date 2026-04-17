<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manga Quilla - About Us</title>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
}

body {
    background: linear-gradient(135deg, #000000, #1a0000);
    color: white;
}


.navbar {
    border-bottom: 1px solid #333;
    padding: 15px 0;
    text-align: center;
}

.navbar ul {
    list-style: none;
}

.navbar ul li {
    display: inline-block;
    margin: 0 25px;
}

.navbar ul li a {
    text-decoration: none;
    color: #aaa;
    font-size: 14px;
}

.navbar ul li a:hover,
.navbar ul li a.active {
    color: white;
}


.about {
    max-width: 1200px;
    margin: auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 100px 20px;
    min-height: 90vh;
}

.about-left {
    max-width: 550px;
}

.about-left h2 {
    margin-bottom: 20px;
    font-size: 20px;
    display: flex;
    align-items: center;
}

.accent {
    width: 4px;
    height: 20px;
    background: red;
    margin-right: 10px;
}

.about-left p {
    font-size: 14px;
    line-height: 1.8;
    color: #ccc;
    margin-bottom: 20px;
}

.about-right {
    text-align: center;
}

.about-right h1 {
    font-size: 60px;
    line-height: 1.1;
}

.about-right span {
    color: red;
}

.footer {
    background: #8b1a14;
    padding: 60px 10%;
    margin-top: 100px;
}

.footer-container {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
}

.footer-column {
    margin-bottom: 20px;
}

.footer-column h4 {
    margin-bottom: 15px;
    font-size: 14px;
}

.footer-column p {
    font-size: 13px;
    color: #ddd;
    margin-bottom: 8px;
}


.subscribe-box {
    background: rgba(255,255,255,0.1);
    padding: 20px;
    width: 250px;
}

.subscribe-box input {
    width: 70%;
    padding: 8px;
    border: none;
}

.subscribe-box button {
    padding: 8px 12px;
    background: black;
    color: white;
    border: none;
    cursor: pointer;
}


.footer-bottom {
    border-top: 1px solid rgba(255,255,255,0.2);
    margin-top: 30px;
    padding-top: 20px;
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
}

.logo {
    font-weight: bold;
}

.logo span {
    color: red;
}




@media (max-width: 900px) {
    .about {
        flex-direction: column;
        text-align: center;
    }

    .about-right {
        text-align: center;
        margin-top: 30px;
    }

    .footer-container {
        flex-direction: column;
        gap: 20px;
    }
}


</style>
</head>

<body>

<header class="navbar">
<nav>
<ul>
<li><a href="#">HOME</a></li>
<li><a href="#">PRODUCTS</a></li>
<li><a href="#">NEW ARRIVALS</a></li>
<li><a href="#" class="active">ABOUT US</a></li>
</ul>
</nav>
</header>

<!-- ABOUT -->
<section class="about">
<div class="about-left">
<h2><span class="accent"></span> ABOUT US</h2>

        <p>
            Welcome to our Manga Online Store, your one-stop destination
            for authentic and high-quality manga collections. We are
            dedicated to bringing your favorite stories closer to you—from
            action-packed adventures to heartfelt slice-of-life series—all in
            one convenient platform.
        </p>

        <p>
            Our mission is to make manga more accessible to everyone by
            providing a smooth and user-friendly shopping experience.
            Whether you’re a long-time collector or a new reader exploring
            the world of manga, we offer a wide selection of titles to suit
            every taste.
        </p>
</div>

<div class="about-right">
<h1>MANGA<br><span>QUILLA.</span></h1>
<p>Manga. We got you.</p>
</div>
</section>

<!-- FOOTER -->
<footer class="footer">

<div class="footer-container">

<div class="footer-column">
<h4>Product</h4>
<p>Manga</p>
<p>New Arrivals</p>
<p>Action</p>
<p>Sci-Fi</p>
</div>

<div class="footer-column">
<h4>Information</h4>
<p>FAQ</p>
<p>Blog</p>
<p>Support</p>
</div>

<div class="footer-column">
<h4>Company</h4>
<p>About us</p>
<p>Careers</p>
<p>Contact us</p>
<p>Lift Media</p>
</div>

<div class="subscribe-box">
<h4>Subscribe</h4>
<input type="text" placeholder="Email address">
<button>→</button>
<p style="margin-top:10px;font-size:12px;">
Hello, we are a dedicated manga online store committed to bringing you authentic manga.
</p>
</div>

</div>

<div class="footer-bottom">
<div class="logo">MANGA<span>QUILLA</span></div>
<div>
Terms &nbsp; Privacy &nbsp; Cookies
</div>
</div>

</footer>

</body>
</html>