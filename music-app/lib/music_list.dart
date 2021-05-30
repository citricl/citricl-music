import 'package:audioplayers/audioplayers.dart';
import 'package:citricl_music/bean/music_bean.dart';
import 'package:citricl_music/res/urlRes.dart';
import 'package:citricl_music/util/network.dart';
import 'package:flutter/material.dart';

class MusicListPage extends StatefulWidget {
  MusicListPage(this.id, this.title, this.albumImg, {Key key})
      : super(key: key);
  final int id;
  final String title;
  final String albumImg;

  @override
  _MusicListPageState createState() => _MusicListPageState();
}

class _MusicListPageState extends State<MusicListPage> {
  AudioPlayer audioPlayer;

  @override
  void initState() {
    super.initState();
    audioPlayer = new AudioPlayer();
    _getPlayList();
  }

  List<MusicBean> musicList = [];

  @override
  Widget build(BuildContext context) {
    AudioPlayer.logEnabled = true;
    return Scaffold(
      body: _initBodyView(),
      floatingActionButton: FloatingActionButton(
        onPressed: null,
        tooltip: 'Increment',
        child: Icon(Icons.menu_outlined),
      ),
    );
  }

  _initBodyView() {
    return CustomScrollView(
      slivers: <Widget>[
        SliverAppBar(
          expandedHeight: 360,
          flexibleSpace: FlexibleSpaceBar(
            title: Text(
              widget.title,
            ),
            background: Image.network(
              widget.albumImg,
              fit: BoxFit.fill,
            ),
          ),
          pinned: true,
          forceElevated: true,
        ),
        SliverList(delegate: SliverChildListDelegate(_buildMusicList()))
      ],
    );
  }

  _buildMusicList() {
    return List.generate(
      musicList.length,
      (index) => Padding(
        padding: EdgeInsets.fromLTRB(5, 5, 5, 5),
        child: Card(
          child: Container(
            decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(10))),
            child: ListTile(
              dense: true,
              title: Text(musicList[index].name),
              subtitle: musicList[index].artist == ""
                  ? null
                  : Text(musicList[index].artist),
              onTap: () => {_play(musicList[index].musicUrl)},
              leading: Image.network(musicList[index].imgUrl),
            ),
          ),
        ),
      ),
    );
  }

  _play(String url) async {
    await audioPlayer.play(url, isLocal: false, volume: 1.0);
  }

  _getPlayList() async {
    var result = await MyDio().get(UrlRes.musicList + "?id=${widget.id}");
    var data = MusicListBean.fromJson(result);
    if (data.flag) {
      data.list.forEach((temp) {
        setState(() {
          musicList.add(MusicBean(
              temp.id, temp.name, temp.artist, temp.imgUrl, temp.musicUrl));
        });
      });
    }
  }
}

class MusicBean {
  int id;
  String name;
  String artist;
  String imgUrl;
  String musicUrl;
  bool isPlaying;

  MusicBean(this.id, this.name, this.artist, this.imgUrl, this.musicUrl,
      {this.isPlaying = false});
}
