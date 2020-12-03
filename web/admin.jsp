<%-- 
    Document   : admin
    Created on : Oct 11, 2020, 9:04:33 PM
    Author     : HOME
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="" />
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Admin - Yellow Moon Shop</title>
        <link rel="icon" type="image/x-icon" href="images/favicon.png"/>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css1/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css1/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css1/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css1/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css1/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css1/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css1/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css1/style.css" type="text/css">
    </head>
    <body>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript">
            $(function() {
            $("#testform").validate({
            rules: {
            txtPostTitle: {
            required: true,
                    rangelength: [6, 100]
            }
            txtPostDescription: {
            required: true
            }

            txtFile: {
            accept: "bmp|png|jpg"
            }
            }
            });
        </script>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <!-- Humberger Begin -->
        <div class="humberger__menu__overlay"></div>
        <div class="humberger__menu__wrapper">
            <div class="humberger__menu__logo">
                <a href="#"><img src="img/logo.png" alt=""></a>
            </div>
            <div class="humberger__menu__cart">
                <ul>
                    <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                    <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                </ul>
                <div class="header__cart__price"></div>
            </div>
            <div class="humberger__menu__widget">
                <div class="header__top__right__language">
                    <img src="img/language.png" alt="">
                    <div>English</div>
                    <span class="arrow_carrot-down"></span>
                    <ul>
                        <li><a href="#">Spanish</a></li>
                        <li><a href="#">English</a></li>
                    </ul>
                </div>
                <div class="header__top__right__auth">
                    <a href="#"><i class="fa fa-user"></i> Login</a>
                </div>
            </div>
            <nav class="humberger__menu__nav mobile-menu">
                <ul>
                    <li class="active"><a href="SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search">Home</a></li>
                    <li><a href="./shop-grid.html">Shop</a></li>
                    <li><a>Pages</a>
                        <ul class="header__menu__dropdown">
                            <c:if test="${sessionScope.ROLE == 'admin'}">
                                <li>
                                    <a href="admin.jsp">Admin Page</a>
                                </li>
                            </c:if> 
                            <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                            <li><a href="./checkout.html">Check Out</a></li>
                        </ul>
                    </li>
                    <li><a href="./blog.html">Blog</a></li>
                    <li><a href="./contact.html">Contact</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
            <div class="header__top__right__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-linkedin"></i></a>
                <a href="#"><i class="fa fa-pinterest-p"></i></a>
            </div>
            <div class="humberger__menu__contact">
                <ul>
                    <li><i class="fa fa-envelope"></i> trungndse140274@fpt.edu.vn</li>
                    <li>Free Shipping for all Order of 199.000đ</li>
                </ul>
            </div>
        </div>
        <!-- Humberger End -->

        <header class="header">
            <div class="header__top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="header__top__left">
                                <ul>
                                    <li><i class="fa fa-envelope"></i> trungndse140274@fpt.edu.vn</li>
                                    <li>Free Shipping for all Order of 199.000đ</li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="header__top__right">
                                <div class="header__top__right__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-linkedin"></i></a>
                                    <a href="#"><i class="fa fa-pinterest-p"></i></a>
                                </div>
                                <div class="header__top__right__language">
                                    <img src="img/language.png" alt="">
                                    <div>English</div>
                                    <span class="arrow_carrot-down"></span>
                                    <ul>
                                        <li><a href="#">English</a></li>
                                        <li><a href="#">Vietnamese</a></li>
                                    </ul>
                                </div>
                                <div class="header__top__right__auth">
                                    <a href="loginPage.jsp"><i class="fa fa-user"></i> ${sessionScope.EMAILID} - ${sessionScope.ROLE}</a>
                                    <form action="LogOutController" method="POST">
                                        <input type="hidden" value="${sessionScope.EMAILID}" name="txtAccID"/>
                                        <input type="submit" value="Log Out" name="action"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="header__logo">
                            <a href="SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search&minPrice=&maxPrice="><img src="images/YellowMoonShop.png" alt=""></a>
                        </div>                      
                    </div>    
                    <div class="col-lg-6">
                        <nav class="header__menu">
                            <ul>
                                <li class="active"><a href="SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search&minPrice=&maxPrice=">Home</a></li>
                                <li><a>Pages</a>
                                    <ul class="header__menu__dropdown">
                                        <c:if test="${sessionScope.ROLE == 'admin'}">
                                            <li><a href="admin.jsp">Admin Page</a></li>
                                            </c:if>             
                                        <li><a href="./shoping-cart.html">Shopping Cart</a></li> <%-- shopping cart --%>
                                        <li><a href="./checkout.html">Check Out</a></li> <%-- bill --%>
                                    </ul>
                                </li>
                                <li><a href="./blog.html">Blog</a></li> <%-- tóm tắt blog --%>
                                <li><a href="./contact.html">Contact</a></li> <%-- liên hệ kịp thì làm --%>
                            </ul>
                        </nav>
                    </div>
                    <div class="col-lg-3">
                        <div class="header__cart">
                            <ul>
                                <li><a href="#"><i class="fa fa-heart"></i> <span></span></a></li> <%-- thông báo --%>
                                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span></span></a></li> <%-- thông báo --%>
                            </ul>
                            <div class="header__cart__price">item: <span>-----đ</span></div> <%-- Tiền còn dư --%>
                        </div>
                    </div>
                </div>
                <div class="humberger__open">
                    <i class="fa fa-bars"></i>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <section class="hero hero-normal">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                    </div>
                    <div class="col-lg-9">
                        <div class="hero__search">
                            <h2>ADMIN MANAGER PAGE - NOT MEMBER</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>ADMIN - Yellow Moon Shop</h2>
                            <div class="breadcrumb__option">
                                <a href="SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search&minPrice=&maxPrice=">Home</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->
        <!-- Manager Section Begin -->
        <section class="product spad">
            <div class="container">
                <div class="col-lg-12">
                    <!--                <div class="hero__search">-->
                    <div class="hero__search__form">
                        <h3>Add Cake Form</h3> 
                        Status: ${requestScope.NOTI}
                        <form name="testform" id="testform" enctype="multipart/form-data" action="AddCakeController" method="POST">
                            Cake Name: <input type="text" placeholder="Input cake name..." name="txtCakeName"><br/>
                            <font color="red">${requestScope.INVALID.cakeNameError}</font><br/><br/>
                            Description: <input type="text" placeholder="Input Description..." name="txtCakeDescription"><br/><br/>
                            Cake Image: <input type="file" name="txtFile"><br/><br/>
                            Price: <input type="number" placeholder="..." name="txtCakePrice"> VND<br/>
                            <font color="red">${requestScope.INVALID.priceCakeError}</font><br/><br/>
                            Quantity: <input type="number" placeholder="..." name="txtCakeQuantity"><br/>
                            <font color="red">${requestScope.INVALID.quantityCakeError}</font><br/><br/>
                            Created Date: <input type="datetime-local" name="txtCreatedDate" min="2000-01-01T00:00" max="2030-12-31T23:59"><br/><br/>
                            <font color="red">${requestScope.INVALID.createDayError}</font><br/><br/>
                            Exp Date: <input type="datetime-local" name="txtExpDate" min="2000-01-01T00:00" max="2030-12-31T23:59"><br/><br/>
                            <font color="red">${requestScope.INVALID.expDateError}</font><br/><br/>
                            <font color="red">${requestScope.INVALID.createExpDateError}</font><br/>          
                            Category: <br/>
                            <select name="cbxCategory">          
                                <c:forEach items="${sessionScope.LISTCATEGORY}" var="list">
                                    <option>${list.categoryID}-${list.categoryName}</option>
                                </c:forEach>
                            </select><br/><br/>
                            <input type="hidden" name="txtCakeStatus" value="Active">
                            <input type="hidden" name="txtAccAdminUpdate" value="${sessionScope.EMAILID}">
                            <input type="submit" value="Add To Shop" name="action"/><br/>
                        </form><br/>
                        <h3>Add Cake Category</h3> 
                        Status: ${requestScope.NOTI2}
                        <form action="AddCategoryController" method="POST">
                            Category Name: <input type="text" placeholder="Input category name..." name="txtCategoryName"><br/><br/>
                            <font color="red">${requestScope.INVALID.categoryNameError}</font<br/><br/>
                            <input type="submit" value="Add Category" name="action"/><br/>
                        </form><br/>
                        <h4>Cake Category</h4><br/>
                        <c:forEach items="${sessionScope.LISTCATEGORY}" var="list" varStatus="counter">
                            <h5>${counter.count}   :   ${list.categoryID}  -  ${list.categoryName}</h5>
                        </c:forEach>
                    </div>
                    <!--                </div>-->
                </div>
            </div>
        </section>
        <!-- Manager Section End -->

        <!-- Footer Section Begin -->
<!--        <footer class="footer spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="footer__about">
                            <div class="footer__about__logo">
                                <a href="SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search&minPrice=&maxPrice="><img src="images/YellowMoonShop.png" alt=""></a>
                            </div>
                            <ul>
                                <li>Address: 54/45B Phạm Văn Sáng, Xuân Thới Thượng, Hóc Môn, TpHCM, VN</li>
                                <li>Phone: +84915723136</li>
                                <li>Email: trungndse140274@fpt.edu.vn</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
                        <div class="footer__widget">
                            <h6>Useful Links</h6>
                            <ul>
                                <li><a href="#">About Us</a></li>
                                <li><a href="#">About Our Shop</a></li>
                                <li><a href="#">Secure Shopping</a></li>
                                <li><a href="#">Delivery infomation</a></li>
                                <li><a href="#">Privacy Policy</a></li>
                                <li><a href="#">Our Sitemap</a></li>
                            </ul>
                            <ul>
                                <li><a href="#">Who We Are</a></li>
                                <li><a href="#">Our Services</a></li>
                                <li><a href="#">Projects</a></li>
                                <li><a href="#">Contact</a></li>
                                <li><a href="#">Innovation</a></li>
                                <li><a href="#">Testimonials</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="footer__widget">
                            <h6>Join Our Newsletter Now</h6>
                            <div class="footer__widget__social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-pinterest"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="footer__copyright">
                            <div class="footer__copyright__text"><p> Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. 
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="#" target="_blank">TrungND</a>
                                     Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. </p></div>
                            <div class="footer__copyright__payment"><img src="img/payment-item.png" alt=""></div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>-->
        <!-- Js Plugins -->
        <script src="js1/jquery-3.3.1.min.js"></script>
        <script src="js1/bootstrap.min.js"></script>
        <script src="js1/jquery.nice-select.min.js"></script>
        <script src="js1/jquery-ui.min.js"></script>
        <script src="js1/jquery.slicknav.js"></script>
        <script src="js1/mixitup.min.js"></script>
        <script src="js1/owl.carousel.min.js"></script>
        <script src="js1/main.js"></script>
    </body>
</html>
