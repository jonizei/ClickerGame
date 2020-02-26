(this["webpackJsonpclicker-game-front-end"]=this["webpackJsonpclicker-game-front-end"]||[]).push([[0],{33:function(e,t,a){e.exports=a(66)},38:function(e,t,a){},39:function(e,t,a){},40:function(e,t,a){},58:function(e,t,a){},60:function(e,t,a){},66:function(e,t,a){"use strict";a.r(t);var n=a(0),s=a.n(n),l=a(30),o=a.n(l),r=(a(38),a(6)),c=a(7),i=a(9),m=a(8),u=a(2),d=a(10),h=(a(39),a(40),function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(i.a)(this,Object(m.a)(t).call(this,e))).handleChange=function(e){e.preventDefault(),"username"===e.target.name?a.setState({username:e.target.value}):"password"===e.target.name&&a.setState({password:e.target.value})},a.handleClick=function(e){e.preventDefault(),a.props.onLogin(a.state.username,a.state.password)},a.handleClick=a.handleClick.bind(Object(u.a)(a)),a.handleChange=a.handleChange.bind(Object(u.a)(a)),a.state={username:"",password:""},a}return Object(d.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){return s.a.createElement("div",{className:"form-container col-xs-12 col-sm-12 col-md-5 w-100 p-5"},s.a.createElement("div",{className:"form-controls w-75"},s.a.createElement("h1",{className:"pb-2"},"Login"),s.a.createElement("p",null,s.a.createElement("input",{className:"form-component w-100 p-2",type:"text",name:"username",placeholder:"Username",onChange:this.handleChange})),s.a.createElement("p",null,s.a.createElement("input",{className:"form-component w-100 p-2",type:"password",name:"password",placeholder:"Password",onChange:this.handleChange})),s.a.createElement("p",null,s.a.createElement("button",{className:"form-component form-btn w-100 p-2",onClick:this.handleClick},"Login"))))}}]),t}(n.Component)),p=a(12),g=a.n(p),f=(a(58),function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(i.a)(this,Object(m.a)(t).call(this,e))).handleClick=function(e){if(e.preventDefault(),a.state.playerDetails.points>0){var t=sessionStorage.getItem("accessToken");g()({method:"post",url:"http://localhost:8080/api/click/",headers:{Authorization:t}}).then((function(e){a.handleReward(e.data)})).catch((function(e){console.log(e)}))}else{var n=sessionStorage.getItem("accessToken");g()({method:"post",url:"http://localhost:8080/api/click/reset",headers:{Authorization:n}}).then((function(e){a.setState({message:"Points resetted",btnText:"Click",playerDetails:e.data})})).catch((function(e){console.log(e)}))}},a.handleClick=a.handleClick.bind(Object(u.a)(a)),a.handleReward=a.handleReward.bind(Object(u.a)(a)),a.state={message:"",btnText:"Click",playerDetails:a.props.playerDetails},a}return Object(d.a)(t,e),Object(c.a)(t,[{key:"handleReward",value:function(e){var t=this.state.playerDetails.points+e.points,a=e.points>0?"You won "+e.points+" points!":"No reward",n="Click";e.requiredClicks>0&&(a+=" Clicks to next reward: "+e.requiredClicks),0===t&&(a="You have runned out of points. Click the button to reset your points",n="Reset"),this.setState({message:a,btnText:n,playerDetails:{username:this.state.playerDetails.username,points:t}})}},{key:"render",value:function(){return s.a.createElement("div",{className:"container"},s.a.createElement("div",{className:"row justify-content-center p-2"},s.a.createElement("div",{className:"col-xs-12 col-sm-12 col-md-2 pb-3 w-100 h-100"},s.a.createElement("button",{className:"logout-btn w-100 mh-25",onClick:this.props.onLogout},"Logout")),s.a.createElement("div",{className:"game-container form-container col-xs-12 col-sm-12 col-md-8 p-5 mw-100 mh-100"},s.a.createElement("div",{className:"d-sm-flex flex-md-row flex-sm-column"},s.a.createElement("div",null,s.a.createElement("button",{className:"game-btn",onClick:this.handleClick},this.state.btnText)),s.a.createElement("div",{className:"flex-column pt-5 pt-sm-5 p-md-5"},s.a.createElement("p",{className:"game-output"},this.state.playerDetails.username),s.a.createElement("p",{className:"game-output"},"Points: ",this.state.playerDetails.points))),s.a.createElement("div",null,s.a.createElement("p",{className:"game-output text-justify"},this.state.message)))))}}]),t}(n.Component)),b=(a(59),a(60),a(15)),v=function(e){function t(){return Object(r.a)(this,t),Object(i.a)(this,Object(m.a)(t).apply(this,arguments))}return Object(d.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){return s.a.createElement("div",{className:"col-sm-12 col-md-3"},s.a.createElement("nav",{className:"navbar navbar-inverse navbar-fixed-left w-100 h-100"},s.a.createElement("ul",{className:"nav navbar-nav w-100 h-100 pb-3"},s.a.createElement("li",null,s.a.createElement(b.b,{to:"/login",className:"nav-item w-100 mh-25 p-2"},"Login")),s.a.createElement("li",null,s.a.createElement(b.b,{to:"/register",className:"nav-item w-100 mh-25 p-2 mt-3"},"Register")))))}}]),t}(n.Component),w=a(13),E=function(e){function t(){var e;return Object(r.a)(this,t),(e=Object(i.a)(this,Object(m.a)(t).call(this))).handleChange=function(t){t.preventDefault(),"username"===t.target.name?e.setState({username:t.target.value}):"password"===t.target.name?e.setState({password:t.target.value}):"confirm-password"===t.target.name&&e.setState({confirmPassword:t.target.value})},e.handleClick=function(t){t.preventDefault(),e.state.password===e.state.confirmPassword?g()({method:"post",url:"http://localhost:8080/api/user/register",data:{username:e.state.username,password:e.state.password}}).then((function(t){alert(t.data),e.setState({username:"",password:"",confirmPassword:""})})):alert("Passwords doesn't match")},e.handleChange=e.handleChange.bind(Object(u.a)(e)),e.handleClick=e.handleClick.bind(Object(u.a)(e)),e.state={username:"",password:"",confirmPassword:""},e}return Object(d.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){return s.a.createElement("div",{className:"form-container col-xs-12 col-sm-12 col-md-5 w-100 p-5"},s.a.createElement("div",{className:"form-controls w-75"},s.a.createElement("h1",{className:"pb-2"},"Register"),s.a.createElement("p",null,s.a.createElement("input",{className:"form-component w-100 p-2",type:"text",value:this.state.username,name:"username",placeholder:"Username",onChange:this.handleChange})),s.a.createElement("p",null,s.a.createElement("input",{className:"form-component w-100 p-2",type:"password",value:this.state.password,name:"password",placeholder:"Password",onChange:this.handleChange})),s.a.createElement("p",null,s.a.createElement("input",{className:"form-component w-100 p-2",type:"password",value:this.state.confirmPassword,name:"confirm-password",placeholder:"Confirm password",onChange:this.handleChange})),s.a.createElement("p",null,s.a.createElement("button",{className:"form-component form-btn w-100 p-2",onClick:this.handleClick},"Register"))))}}]),t}(n.Component),k=function(e){function t(){var e;return Object(r.a)(this,t),(e=Object(i.a)(this,Object(m.a)(t).call(this))).handleLogin=e.handleLogin.bind(Object(u.a)(e)),e.handleLogout=e.handleLogout.bind(Object(u.a)(e)),e.loadPlayerDetails=e.loadPlayerDetails.bind(Object(u.a)(e)),e.state={loggedIn:!1,isLoadingDetails:!0,playerDetails:{}},e}return Object(d.a)(t,e),Object(c.a)(t,[{key:"componentDidMount",value:function(){sessionStorage.getItem("accessToken")&&(this.loadPlayerDetails(),this.setState({loggedIn:!0}))}},{key:"handleLogin",value:function(e,t){var a=this;g.a.post("http://localhost:8080/login",{username:e,password:t}).then((function(e){sessionStorage.setItem("accessToken",e.headers.authorization),a.setState({loggedIn:!0,isLoadingDetails:!0},a.loadPlayerDetails)})).catch((function(e){console.log(e)}))}},{key:"handleLogout",value:function(){this.setState({loggedIn:!1,playerDetails:{}},(function(){sessionStorage.removeItem("accessToken")}))}},{key:"loadPlayerDetails",value:function(){var e=this,t=sessionStorage.getItem("accessToken");g()({method:"get",url:"http://localhost:8080/api/user/details",headers:{Authorization:t}}).then((function(t){e.setState({playerDetails:t.data,isLoadingDetails:!1})}))}},{key:"render",value:function(){var e=function(){return s.a.createElement(w.a,{to:"/"})};return this.state.loggedIn?this.state.isLoadingDetails?s.a.createElement("p",null,"Loading..."):s.a.createElement(w.d,null,s.a.createElement(w.b,{exact:!0,path:"/"},s.a.createElement("div",{className:"d-flex align-items-center min-vh-100"},s.a.createElement(f,{playerDetails:this.state.playerDetails,onLogout:this.handleLogout}))),s.a.createElement(w.b,{component:e})):s.a.createElement("div",{className:"d-flex align-items-center min-vh-100"},s.a.createElement("div",{className:"container"},s.a.createElement("div",{className:"row"},s.a.createElement(v,null),s.a.createElement(w.d,null,s.a.createElement(w.b,{exact:!0,path:"/"},s.a.createElement(h,{onLogin:this.handleLogin})),s.a.createElement(w.b,{path:"/login"},s.a.createElement(h,{onLogin:this.handleLogin})),s.a.createElement(w.b,{path:"/register"},s.a.createElement(E,null)),s.a.createElement(w.b,{component:e})))))}}]),t}(n.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var C=s.a.createElement(b.a,null,s.a.createElement(k,null));o.a.render(C,document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()}))}},[[33,1,2]]]);
//# sourceMappingURL=main.39430253.chunk.js.map