<%-- 
    Document   : footer
    Created on : Oct 28, 2021, 2:16:47 PM
    Author     : Chinh
--%>

<!-- Start Footer Area -->
<footer class="footer">
    <!-- Footer Top -->
    <div class="footer-top section">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-6 col-12">
                    <!-- Single Widget -->
                    <div class="single-footer about">
                        <div class="logo">
                            <a href="."><img src="asset/images/logo2.png" alt="#"></a>
                        </div>
                        <p class="call">Got Question? Contact me<span><a href="#">chinhldse04423@fpt.edu.vn</a></span></p>
                    </div>
                    <!-- End Single Widget -->
                </div>
                <div class="col-lg-2 col-md-6 col-12">
                    <!-- Single Widget -->
                    <div class="single-footer links">
                        <h4>Links</h4>
                        <ul>
                            <% if (privilege != 1) {
                                    if (session.getAttribute("customer") != null) {%>
                            <li><a href="user">User info</a></li>
                            <li><a href="logout">Logout</a></li>
                                <%} else {%>
                            <li><a href="login">Login</a></li>
                            <li><a href="signup">Sign Up</a></li>
                            <li><a href="admin">Admin</a></li>
                                <%}
                                } else { %>
                            <li><a href="#">Admin info</a></li>
                                <%}%>
                        </ul>
                    </div>
                    <!-- End Single Widget -->
                </div>
                <div class="col-lg-2 col-md-6 col-12">
                    <!-- Single Widget -->
                    <div class="single-footer links">
                        <h4>Service</h4>
                        <ul>
                            <% if (privilege == 1) {%>
                            <li><a href="MngProd">List Product</a></li>
                            <li><a href="MngCate">List Category</a></li>
                            <li><a href="MngCust">List Customer</a></li>
                            <li><a href="admin">List Admin</a></li>
                            <li><a href="mngBill">List Bill</a></li>
                                <%} else { %>
                            <li><a href=".">Shopping</a></li>
                            <li><a href="cart">Cart</a></li>
                            <li><a href="checkout">Checkout</a></li>
                                <%}%>
                        </ul>
                    </div>
                    <!-- End Single Widget -->
                </div>
                <div class="col-lg-3 col-md-6 col-12">
                    <!-- Single Widget -->
                    <div class="single-footer social">
                        <h4>Get In Touch</h4>
                        <!-- Single Widget -->
                        <div class="contact">
                            <ul>
                                <li>chinhldse04423@fpt.edu.vn</li>
                            </ul>
                        </div>
                        <!-- End Single Widget -->
                    </div>
                    <!-- End Single Widget -->
                </div>
            </div>
        </div>
    </div>
    <!-- End Footer Top -->
    <div class="copyright">
        <div class="container">
            <div class="inner">
                <div class="row">
                    <div class="col-lg-6 col-12">
                        <div class="left">
                            <p>Copyright © 2020 <a href="#">ChinhLD</a>  -  All Rights Reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- /End Footer Area -->

<!-- Jquery -->
<script src="asset/js/jquery.min.js"></script>
<script src="asset/js/jquery-migrate-3.0.0.js"></script>
<script src="asset/js/jquery-ui.min.js"></script>
<!-- Popper JS -->
<script src="asset/js/popper.min.js"></script>
<!-- Bootstrap JS -->
<script src="asset/js/bootstrap.min.js"></script>
<!-- Color JS -->
<script src="asset/js/colors.js"></script>
<!-- Slicknav JS -->
<script src="asset/js/slicknav.min.js"></script>
<!-- Owl Carousel JS -->
<script src="asset/js/owl-carousel.js"></script>
<!-- Magnific Popup JS -->
<script src="asset/js/magnific-popup.js"></script>
<!-- Waypoints JS -->
<script src="asset/js/waypoints.min.js"></script>
<!-- Countdown JS -->
<script src="asset/js/finalcountdown.min.js"></script>
<!-- Nice Select JS -->
<script src="asset/js/nicesellect.js"></script>
<!-- Flex Slider JS -->
<script src="asset/js/flex-slider.js"></script>
<!-- ScrollUp JS -->
<script src="asset/js/scrollup.js"></script>
<!-- Onepage Nav JS -->
<script src="asset/js/onepage-nav.min.js"></script>
<!-- Easing JS -->
<script src="asset/js/easing.js"></script>
<!-- Active JS -->
<script src="asset/js/active.js"></script>