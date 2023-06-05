// 定义 showMenu 函数，用于控制导航栏展开和缩回
const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId); // 获取导航栏切换按钮
    const navbar = document.getElementById(navbarId); // 获取导航栏
    const bodypadding = document.getElementById(bodyId); // 获取页面主体

    // 判断导航栏切换按钮和导航栏是否存在
    if (toggle && navbar) {
        // 为导航栏切换按钮添加点击事件
        toggle.addEventListener('click', () => {
            // 切换导航栏的 'expander' 类
            navbar.classList.toggle('expander')
            // 切换页面主体的 'body-pd' 类
            bodypadding.classList.toggle('body-pd')
        })
    }
}

// 调用 showMenu 函数，实现导航栏控制功能
showMenu('nav-toggle', 'navbar', 'body-pd')

// 获取所有导航链接，为它们添加点击事件
const linkColor = document.querySelectorAll(".nav_link")
function colorLink() {
    // 移除所有导航链接的 'active' 类
    linkColor.forEach(l => l.classList.remove('active'))
    // 为当前点击的导航链接添加 'active' 类
    this.classList.add('active')
}
linkColor.forEach(l => l.addEventListener('click', colorLink))

// 获取所有折叠菜单链接，为它们添加点击事件
const linkCollapse = document.getElementsByClassName('collapse__link')
var i
for (i = 0; i < linkCollapse.length; i++) {
    linkCollapse[i].addEventListener('click', function () {
        const collapseMenu = this.nextElementSibling // 获取对应的折叠菜单
        collapseMenu.classList.toggle('showCollapse') // 切换折叠菜单的 'showCollapse' 类

        const rotate = collapseMenu.previousElementSibling // 获取折叠菜单的前一个元素（即图标元素）
        rotate.classList.toggle("") // 切换图标的样式类
    })
}