/// flag : true
/// list : [{"id":2,"title":"年轻之歌 有关爱与挑衅","desc":"那些喜欢到会循环播放的歌","imgUrl":"localhost:8888/img/songListPic/wv2NdfZrUnLDSwk_kQoSZg==_109951163751040922.jpg"},{"id":3,"title":"希望十八岁你爱的人是八十岁在你身边的人","desc":"让你怦然心动","imgUrl":"localhost:8888/img/songListPic/q0ZyCw22PCiTG2LX_A2kew==_109951163594989759.jpg"},{"id":4,"title":"你的青春里有没有属于你的一首歌？","desc":"关于青春里的那首歌，唱的是你和谁的回忆呢？那年你们有什么故事？\n\n总是有许多的记忆，是关于青春的。\n\n青春时埋下的那份躁动，总会在多年后，装饰着笑容。\n\n总是有许多的遗憾，是关于青春的。\n\n青春时还没来得及表达的情感，总会在多年以后，偶尔的左右着悲欢。\n\n那些最美年华里的相遇，那些青春里的不知所措，都被勾勒成了一幅幅画。\n\n而这些画，只在心情最愉悦时，只在心情最低落时，悄悄的，在内心深处闪过。","imgUrl":"localhost:8888/img/songListPic/109951163271025942.jpg"},{"id":5,"title":"那些喜欢到循环播放的歌","desc":"那些喜欢到会循环播放的歌\n\n感谢收听","imgUrl":"localhost:8888/img/songListPic/109951163609743240.jpg"},{"id":6,"title":"林俊杰的正确打开方式【路人请参考简介】","desc":"这是一个一定要顺序播放并且不切歌才能发现其中奥妙的歌单。\n这是一个可以完美呈现林俊杰音乐态度的歌单。\n这是一个林俊杰的立体化打开方式。","imgUrl":"localhost:8888/img/songListPic/19080924789030458.jpg"},{"id":7,"title":"高 级 感 vlog 纯音乐 BGM","desc":"歌单","imgUrl":"localhost:8888/img/songListPic/vLSB9-NGsd4CLYf_4ShGww==_109951163609572271.jpg"},{"id":8,"title":"世界上很好听的纯音乐(经典不朽)","desc":"歌单","imgUrl":"localhost:8888/img/songListPic/92NWlGo76ha-if-WMK3vCg==_1410673428769729.jpg"},{"id":9,"title":"『粤语』好听到爆的粤语歌单","desc":"歌单","imgUrl":"localhost:8888/img/songListPic/QHD2Uy2y9ktndbK1UKgdgg==_18611433325258133.jpg"},{"id":10,"title":"韩剧OST｜祝你走过半生，仍有颗少女心","desc":"歌单","imgUrl":"localhost:8888/img/songListPic/zhunizouguobansheng.jpg"},{"id":11,"title":"我喜欢你，喜欢没用，没用也喜欢","desc":"情不知所起，一往而情深。\n伤不知所因，痛彻心扉\n\n从前你是我心上的一束光，倾世温暖。\n现在你是我心里的一根刺，刻骨铭心。\n以后你是我心底的一粒尘，无关痛痒。\n\n我喜欢你，喜欢没用，没用也喜欢","imgUrl":"localhost:8888/img/songListPic/109951163919069037.jpg"},{"id":12,"title":"生活感到疲惫的话就听这些歌吧","desc":"当你感到疲惫的时候\n睡一个沉稳的觉醒来\n和陌生人对视互笑\n买一杯刚好温度的奶茶\n吃到合口味的菜\n遇见喜欢人的时候自己是最美的状态\n下雨 清晨 初雪 深夜 亲吻 拥抱 牵手 大笑\n快乐总被说很难\n但我希望你顶...","imgUrl":"localhost:8888/img/songListPic/109951163936991203.jpg"},{"id":13,"title":"熬夜和想你，我都该戒掉了","desc":"命运似乎是一个轮回，在一次次的偶然下，平行线交叉，再平行，故事始终有\"然后\"，可后来的我们，都学会如何去爱了吗？\n\n如果当时你没走，后来的我们会不会不一样。或许，我们每个人都想回到故事最开始的地方。","imgUrl":"localhost:8888/img/songListPic/109951163216834301.jpg"},{"id":14,"title":"怀旧向||时光流转从前，人生如寄","desc":"岁月悠扬，娓娓动听\n在流失了的记忆之中\n听到属于我们这一代人的歌\n想起属于我们这一代人的路\n愿\n星辰大海\n春暖花开","imgUrl":"localhost:8888/img/songListPic/109951163443093546.jpg"},{"id":15,"title":"不曾刻骨铭心，为何念念不忘？","desc":"“所爱隔山海 山海皆可平”\n你拒绝的 不珍惜的 不代表别人也不喜欢\n人生都是向前走的 我们都一样\n\n谁先认真谁先输，我只能说我输了\n再忙碌还是会想你 真的不明白\n都说未曾刻骨铭心，又为何总是念念不忘","imgUrl":"localhost:8888/img/songListPic/109951163887710551.jpg"},{"id":16,"title":"社会语录！土嗨","desc":"社会！","imgUrl":"localhost:8888/img/songListPic/109951163858422257.jpg"}]

class PlaylistBean {
  bool _flag;
  List<MusicItem> _list;

  bool get flag => _flag;

  List<MusicItem> get list => _list;

  PlaylistBean({bool flag, List<MusicItem> list}) {
    _flag = flag;
    _list = list;
  }

  PlaylistBean.fromJson(dynamic json) {
    _flag = json["flag"];
    if (json["list"] != null) {
      _list = [];
      json["list"].forEach((v) {
        _list.add(MusicItem.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    map["flag"] = _flag;
    if (_list != null) {
      map["list"] = _list.map((v) => v.toJson()).toList();
    }
    return map;
  }
}

/// id : 2
/// title : "年轻之歌 有关爱与挑衅"
/// desc : "那些喜欢到会循环播放的歌"
/// imgUrl : "localhost:8888/img/songListPic/wv2NdfZrUnLDSwk_kQoSZg==_109951163751040922.jpg"

class MusicItem {
  int _id;
  String _title;
  String _desc;
  String _imgUrl;

  int get id => _id;

  String get title => _title;

  String get desc => _desc;

  String get imgUrl => _imgUrl;

  MusicItem({int id, String title, String desc, String imgUrl}) {
    _id = id;
    _title = title;
    _desc = desc;
    _imgUrl = imgUrl;
  }

  MusicItem.fromJson(dynamic json) {
    _id = json["id"];
    _title = json["title"];
    _desc = json["desc"];
    _imgUrl = json["imgUrl"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    map["id"] = _id;
    map["title"] = _title;
    map["desc"] = _desc;
    map["imgUrl"] = _imgUrl;
    return map;
  }
}
