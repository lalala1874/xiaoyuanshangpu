<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SUI Mobile Demo</title>
    <meta name="description" content="MSUI: Build mobile apps with simple HTML, CSS, and JS components.">
    <meta name="author" content="阿里巴巴国际UED前端">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <!-- Google Web Fonts -->

    <link rel="stylesheet" href="/dist/css/sm.css">
    <link rel="stylesheet" href="/dist/css/sm-extend.css">
    <link rel="stylesheet" href="/assets/css/demos.css">

    <link rel="apple-touch-icon-precomposed" href="/assets/img/apple-touch-icon-114x114.png">
    <script src="/assets/js/zepto.js"></script>
    <script src="/assets/js/config.js"></script>
    <script>
        //ga
    </script>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?ba76f8230db5f616edc89ce066670710";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<body>
<div class="page-group">
    <div id="page-label-input" class="page">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="/demos/form">
                <span class="icon icon-left"></span>
                返回
            </a>
            <h1 class="title">表单</h1>
        </header>
        <div class="content">
            <div class="list-block">
                <ul>
                    <!-- Text inputs -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Name</div>
                                <div class="item-input">
                                    <input type="text" placeholder="Your name">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">E-mail</div>
                                <div class="item-input">
                                    <input type="email" placeholder="E-mail">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Password</div>
                                <div class="item-input">
                                    <input type="password" placeholder="Password" class="">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Gender</div>
                                <div class="item-input">
                                    <select>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- Date -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Birth date</div>
                                <div class="item-input">
                                    <input type="date" placeholder="Birth day" value="2014-04-30">
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- Switch (Checkbox) -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Switch</div>
                                <div class="item-input">
                                    <label class="label-switch">
                                        <input type="checkbox">
                                        <div class="checkbox"></div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">Textarea</div>
                                <div class="item-input">
                                    <textarea></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content-block">
                <div class="row">
                    <div class="col-50"><a href="#" class="button button-big button-fill button-danger">取消</a></div>
                    <div class="col-50"><a href="#" class="button button-big button-fill button-success">提交</a></div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="/dist/js/sm.js"></script>
<script src="/dist/js/sm-extend.js"></script>
<script src="/dist/js/sm-city-picker.js"></script>
<script src="/assets/js/demos.js"></script>
</body>
</html>
