<%-- 
    Document   : loginPage
    Created on : Oct 9, 2020, 9:12:08 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Shop</title>
        <link rel="stylesheet" type="text/css" href="css/style_login.css"/>

    </head>
    <body>        
        <div class="cont">
            <div class="form sign-in">
                <form action="LoginController" method="POST">              
                    <h2>Welcome back,</h2>
                    <label>
                        <span>Email</span>
                        <input type="text" name="txtEmail" value="${param.txtEmail}"/>
                        <font color="red">${requestScope.INVALID.emailError}</font>
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="txtPassword"/>
                        <font color="red">${requestScope.INVALID.passwordError}</font>
                    </label>
                    <center><font color="red">${requestScope.ERROR}</font></center>
                    <p class="forgot-pass">Forgot password?</p>
                    <input type="hidden" name="txtSearch" value="">
                    <input type="hidden" name="txtSearchID" value="">
                    <input type="hidden" name="PAGEID" value="1">
                    <input type="hidden" name="ROLE_GUEST" value="NotGuest">
                    <input type="hidden" name="minPrice" value="">
                    <input type="hidden" name="maxPrice" value="">
                    <input type="submit" class="submit" name="action" value="Sign In"/>               
                </form>
                <br/> 
                <h3>If not want to be a Member, </h3>
                <form action="LoginController" method="POST">
                    <input type="hidden" name="ROLE_GUEST" value="Guest">
                    <input type="hidden" name="txtSearch" value=""> 
                    <input type="hidden" name="txtSearchID" value="">
                    <input type="hidden" name="PAGEID" value="1">
                    <input type="hidden" name="minPrice" value="">
                    <input type="hidden" name="maxPrice" value="">
                    <input type="submit" class="submit" name="action" value="Login With GUEST"/>         
                </form>
                <img class="img_under" src="https://media3.giphy.com/media/sIIhZliB2McAo/giphy.gif?cid=ecf05e476owj3209n1979x92oa8fu25gbw0iqeetv4l3rdry&rid=giphy.gif"/>
            </div>
            <div class="sub-cont">
                <font color="red"><h3>${requestScope.DUPLICATE}</h3></font>
                <form action="SignUpController" method="POST">
                    <div class="img">
                        <div class="img__text m--up">
                            <h2>New here?</h2>
                            <p>Sign up and discover great amount of new opportunities!</p>
                        </div>
                        <div class="img__text m--in">
                            <h2>One of us?</h2>
                            <p>If you already has an account, just sign in. We've missed you!</p>
                        </div>
                        <div class="img__btn">
                            <span class="m--up">Sign Up</span>
                            <span class="m--in">Sign In</span>
                        </div>
                    </div>
                    <div class="form sign-up">
                        <h2>Time to feel like home,</h2>
                        <label>
                            <span>Email</span>
                            <input type="text" name="txtAccID" value="${param.txtAccID}"/>
                            <font color="red">${requestScope.INVALID.accIDError}</font>
                        </label>
                        <label>
                            <span>Name</span>
                            <input type="text" name="txtName" value="${param.txtName}"/>
                            <font color="red">${requestScope.INVALID.nameError}</font>
                        </label>
                        <label>
                            <span>Password</span>
                            <input type="password" name="txtPassword"/>
                            <font color="red">${requestScope.INVALID.passwordError}</font>
                        </label>
                        <label>
                            <span>Confirm</span>
                            <input type="password" name="txtPasswordCon"/>
                            <font color="red">${requestScope.INVALID.passwordConError}</font>
                        </label>
                        <label>
                            <span>Address</span>
                            <input type="text" name="txtAddress" placeholder="---Optional---"/>
                        </label>
                        <input type="hidden" name="txtRole" value="member"/>
                        <input type="hidden" name="txtStatus" value="New"/>                 
                        <input type="submit" class="submit" value="Create New Account" name="action"/>
                        <!--                    <button type="button" class="fb-btn">Join with <span>facebook</span></button>-->
                    </div>
                </form>
            </div>
        </div>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
