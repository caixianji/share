!function(e){var t={};function a(c){if(t[c])return t[c].exports;var n=t[c]={i:c,l:!1,exports:{}};return e[c].call(n.exports,n,n.exports,a),n.l=!0,n.exports}a.m=e,a.c=t,a.d=function(e,t,c){a.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:c})},a.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,t){if(1&t&&(e=a(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var c=Object.create(null);if(a.r(c),Object.defineProperty(c,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var n in e)a.d(c,n,function(t){return e[t]}.bind(null,n));return c},a.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return a.d(t,"a",t),t},a.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},a.p="",a(a.s=5)}({5:function(e,t,a){"use strict";var c=function(){function e(e,t){for(var a=0;a<t.length;a++){var c=t[a];c.enumerable=c.enumerable||!1,c.configurable=!0,"value"in c&&(c.writable=!0),Object.defineProperty(e,c.key,c)}}return function(t,a,c){return a&&e(t.prototype,a),c&&e(t,c),t}}();var n=function(e){function t(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var e=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this)),a=common.param("cust");return e.state={cust:a?JSON.parse(a):{}},e}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,React.Component),c(t,[{key:"componentDidMount",value:function(){var e=this;MF.setTitle("编辑受益人"),APP.dict("cert,relation",function(t){e.setState({certTypeDict:t.cert,relationDict:t.relation})})}},{key:"close",value:function(){this.state.cust.name=this.refs.name.value,this.state.cust.certNo=this.refs.certNo.value,this.state.cust.scale=this.refs.scale.value,MF.back(JSON.stringify(this.state.cust))}},{key:"onValChange",value:function(e,t){this.state.cust[e]=t,this.setState({cust:this.state.cust})}},{key:"render",value:function(){var e=this,t=this.state.cust;return React.createElement("div",null,React.createElement("div",{className:"bg-white text18",style:{height:"80px",lineHeight:"80px",textAlign:"center"}},"受益人信息"),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"姓名"),React.createElement("div",{className:"form-item-widget"},React.createElement("input",{className:"mt-1",ref:"name",defaultValue:t.name,placeholder:"请输入受益人姓名"}))),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"证件类型"),React.createElement("div",{className:"form-item-widget",onClick:function(t){APP.pick("select",e.state.certTypeDict,e.onValChange.bind(e,"certType"))}},React.createElement("div",{className:(null==t.certType?"tc-gray ":"")+"text16 ml-1 mr-auto"},null==t.certType?"请选择证件类型":this.state.certTypeDict[t.certType]),React.createElement("img",{className:"mt-2 mr-0",style:{width:"27px",height:"39px"},src:"../images/right.png"}))),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"证件号码"),React.createElement("div",{className:"form-item-widget"},React.createElement("input",{className:"mt-1",ref:"certNo",defaultValue:t.certNo,placeholder:"请输入证件号码"}))),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"与被保险人关系"),React.createElement("div",{className:"form-item-widget",onClick:function(t){APP.pick("select",e.state.relationDict,e.onValChange.bind(e,"relation"))}},React.createElement("div",{className:(null==t.relation?"tc-gray ":"")+"text16 ml-1 mr-auto"},null==t.relation?"请选择与被保险人关系":this.state.relationDict[t.relation]),React.createElement("img",{className:"mt-2 mr-0",style:{width:"27px",height:"39px"},src:"../images/right.png"}))),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"受益次序"),React.createElement("div",{className:"form-item-widget",onClick:function(t){APP.pick("select",[1,2,3,4,5,6],e.onValChange.bind(e,"sequence"))}},React.createElement("div",{className:(null==t.relation?"tc-gray ":"")+"text16 ml-1 mr-auto"},null==t.sequence?"请选择与被保险人关系":"第"+(t.sequence+1)+"顺位"),React.createElement("img",{className:"mt-2 mr-0",style:{width:"27px",height:"39px"},src:"../images/right.png"}))),React.createElement("div",{className:"form-item text16"},React.createElement("div",{className:"form-item-label"},"受益比例"),React.createElement("div",{className:"form-item-widget"},React.createElement("input",{className:"mt-1",ref:"scale",defaultValue:t.scale,placeholder:"请输入受益比例"}))),React.createElement("div",{className:"btn-fl text18 tc-white bg-primary",onClick:this.close.bind(this)},"确定"))}}]),t}();$(document).ready(function(){ReactDOM.render(React.createElement(n,null),document.getElementById("root"))})}});