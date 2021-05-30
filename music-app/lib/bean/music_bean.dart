/// flag : true
/// list : [{"id":5,"name":"张杰-逆战","artist":"张杰","imgUrl":"https://music.zlza.icu:8443/img/songPic/nizhan.jpg","musicUrl":"https://music.zlza.icu:8443/song/张杰-逆战.mp3"},{"id":7,"name":"张杰-何必在一起","artist":"张杰","imgUrl":"https://music.zlza.icu:8443/img/songPic/hebiyaozaiyiqi.jpg","musicUrl":"https://music.zlza.icu:8443/song/张杰-何必在一起.mp3"},{"id":11,"name":"周杰伦-告白气球","artist":"周杰伦","imgUrl":"https://music.zlza.icu:8443/img/songPic/gaobaiqiqui.jpg","musicUrl":"https://music.zlza.icu:8443/song/周杰伦-告白气球.mp3"},{"id":22,"name":"林允儿-小幸运","artist":"林允儿","imgUrl":"https://music.zlza.icu:8443/img/songPic/Blossom.jpg","musicUrl":"https://music.zlza.icu:8443/song/林允儿-小幸运.mp3"},{"id":10,"name":"周杰伦&潘儿-夜的第七章","artist":"周杰伦","imgUrl":"https://music.zlza.icu:8443/img/songPic/yedediqizhang.jpg","musicUrl":"https://music.zlza.icu:8443/song/周杰伦&潘儿-夜的第七章.mp3"}]

class MusicListBean {
  bool _flag;
  List<Music> _list;

  bool get flag => _flag;

  List<Music> get list => _list;

  MusicListBean({bool flag, List<Music> list}) {
    _flag = flag;
    _list = list;
  }

  MusicListBean.fromJson(dynamic json) {
    _flag = json["flag"];
    if (json["list"] != null) {
      _list = [];
      json["list"].forEach((v) {
        _list.add(Music.fromJson(v));
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

/// id : 5
/// name : "张杰-逆战"
/// artist : "张杰"
/// imgUrl : "https://music.zlza.icu:8443/img/songPic/nizhan.jpg"
/// musicUrl : "https://music.zlza.icu:8443/song/张杰-逆战.mp3"

class Music {
  int _id;
  String _name;
  String _artist;
  String _imgUrl;
  String _musicUrl;

  int get id => _id;

  String get name => _name;

  String get artist => _artist;

  String get imgUrl => _imgUrl;

  String get musicUrl => _musicUrl;

  Music({int id, String name, String artist, String imgUrl, String musicUrl}) {
    _id = id;
    _name = name;
    _artist = artist;
    _imgUrl = imgUrl;
    _musicUrl = musicUrl;
  }

  Music.fromJson(dynamic json) {
    _id = json["id"];
    _name = json["name"];
    _artist = json["artist"];
    _imgUrl = json["imgUrl"];
    _musicUrl = json["musicUrl"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    map["id"] = _id;
    map["name"] = _name;
    map["artist"] = _artist;
    map["imgUrl"] = _imgUrl;
    map["musicUrl"] = _musicUrl;
    return map;
  }
}
