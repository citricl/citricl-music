webpackJsonp([1],{"9Co0":function(t,i,s){t.exports=s.p+"static/img/turntable-1337986_640.76af304.jpg"},HXef:function(t,i,s){"use strict";Object.defineProperty(i,"__esModule",{value:!0});var n=[{picImg:s("JXuB")},{picImg:s("indR")},{picImg:s("XZxS")},{picImg:s("rKzr")},{picImg:s("LyFs")},{picImg:s("Ma6W")},{picImg:s("rzl+")},{picImg:s("9Co0")}],e={name:"swiper",props:{swiperList:Array}},c={render:function(){var t=this.$createElement,i=this._self._c||t;return i("div",{staticClass:"swiper"},[i("div",{staticClass:"swiper-container"},[i("el-carousel",{attrs:{interval:4e3,type:"card",height:"280px"}},this._l(this.swiperList,function(t,s){return i("el-carousel-item",{key:s},[i("img",{attrs:{src:t.picImg}})])}),1)],1)])},staticRenderFns:[]};var r=s("VU/8")(e,c,!1,function(t){s("bU6Y")},"data-v-234b6c22",null).exports,a=s("R6it"),o=s("gyMJ"),p={name:"home",components:{Swiper:r,ContentList:a.a},data:function(){return{swiperList:[],songList:[],singerList:[]}},created:function(){this.swiperList=n,this.getSongList(),this.getSingerList()},methods:{getSongList:function(){var t=this;Object(o.i)().then(function(i){t.songList=i.sort().slice(0,10)}).catch(function(t){console.log(t)})},getSingerList:function(){var t=this;Object(o.d)().then(function(i){t.singerList=i.sort().slice(0,10)}).catch(function(t){console.log(t)})}}},g={render:function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",{staticClass:"home"},[s("swiper",{attrs:{swiperList:t.swiperList}}),t._v(" "),s("div",{staticClass:"section"},[s("div",{staticClass:"section-title"},[t._v("歌单")]),t._v(" "),s("content-list",{attrs:{contentList:t.songList,path:"song-list-album"}})],1),t._v(" "),s("div",{staticClass:"section"},[s("div",{staticClass:"section-title"},[t._v("歌手")]),t._v(" "),s("content-list",{attrs:{contentList:t.singerList,path:"singer-album"}})],1)],1)},staticRenderFns:[]};var u=s("VU/8")(p,g,!1,function(t){s("auyy")},"data-v-00d7a165",null);i.default=u.exports},JXuB:function(t,i,s){t.exports=s.p+"static/img/blur-1851426_640.3976b58.jpg"},LyFs:function(t,i,s){t.exports=s.p+"static/img/microphone-1209816_640.6a642ac.jpg"},Ma6W:function(t,i,s){t.exports=s.p+"static/img/music-notes-3221097_640.327b9c6.jpg"},XZxS:function(t,i,s){t.exports=s.p+"static/img/boy-984293_640.1fac6a5.jpg"},auyy:function(t,i){},bU6Y:function(t,i){},indR:function(t,i,s){t.exports=s.p+"static/img/concert-768722_640.c629094.jpg"},rKzr:function(t,i,s){t.exports=s.p+"static/img/ipad-605439_640.6085806.jpg"},"rzl+":function(t,i,s){t.exports=s.p+"static/img/piano-1655558_640.a81db1c.jpg"}});