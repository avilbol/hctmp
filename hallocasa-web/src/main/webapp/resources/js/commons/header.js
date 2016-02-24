function menu() {
    document.getElementsByClassName("topnav")[0].classList.toggle("responsive");
}

$(document).ready(function () {
    var MOBILE_MAX_WIDTH = 480;
    var MODE_DESKTOP = 'desktop';
    var MODE_MOBILE = 'mobile';

    var globalMenu = {
        mode: 'desktop',
        selector: '.js-header-global-menu',
        shadowClass: 'ui-shadow',
        buttonSelector: '.js-header-global-mobile-button',
        visible: false,
        init: function () {
            globalMenu.defineMode();

            // switch on resizing between mobile or desktop
            $(window).resize(function () {
                globalMenu.defineMode();
            });

            // hides menu with click in any other element
            $(document).click(function (e) {
                var target = e.target;
                if (!$(target).is(globalMenu.selector) &&
                        !$(target).parents().is(globalMenu.selector) &&
                        !$(target).is(globalMenu.buttonSelector)) {
                    globalMenu.hide();
                }
            });

            // button click to show menu
            $(globalMenu.buttonSelector).click(function () {
                globalMenu.show();
            });
        },
        setAsMobile: function () {
            if (globalMenu.mode !== MODE_MOBILE) {
                globalMenu.mode = MODE_MOBILE;
                $(globalMenu.selector).addClass(globalMenu.shadowClass);
                $(globalMenu.selector).css('display', 'none');
                globalMenu.visible = false;
            }
        },
        setAsDesktop: function () {
            if (globalMenu.mode !== MODE_DESKTOP) {
                globalMenu.mode = MODE_DESKTOP;
                $(globalMenu.selector).removeClass(globalMenu.shadowClass);
                $(globalMenu.selector).css('display', 'block');
            }
        },
        show: function () {
            if (!globalMenu.visible) {
                $(globalMenu.selector).css('display', 'block');
                globalMenu.visible = true;
            }
        },
        hide: function () {
            if (globalMenu.mode === MODE_MOBILE && globalMenu.visible) {
                $(globalMenu.selector).css('display', 'none');
                globalMenu.visible = false;
            }
        },
        defineMode: function () {
            var windowWidth = $(window).width();
            if ((windowWidth) <= MOBILE_MAX_WIDTH) {
                globalMenu.setAsMobile();
            } else {
                globalMenu.setAsDesktop();
            }
        }
    };

    globalMenu.init();
});

