import 'package:citricl_music/bean/playlist_bean.dart';
import 'package:citricl_music/res/urlRes.dart';
import 'package:citricl_music/util/network.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyrefresh/easy_refresh.dart';

import 'music_list.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: "home",
      theme: ThemeData(
        primarySwatch: Colors.red,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      darkTheme: ThemeData(
        primarySwatch: Colors.red,
        brightness: Brightness.dark,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      routes: {"home": (context) => MyHomePage()},
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  EasyRefreshController _controller = EasyRefreshController();
  List<PlayListBean> playList = [];
  var playListOffset = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Citricl Music'),
      ),
      body: Center(
        child: EasyRefresh(
          controller: _controller,
          enableControlFinishLoad: true,
          enableControlFinishRefresh: true,
          onRefresh: () async {
            playList.clear();
            playListOffset = 0;
            _getPlayList();
          },
          onLoad: () async {
            Future.delayed(Duration(seconds: 1), () {
              _getPlayList();
            });
          },
          child: SingleChildScrollView(
            child: _buildPlayList(),
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: null,
        tooltip: 'Increment',
        child: Icon(Icons.menu_outlined),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    _getPlayList();
  }

  _buildPlayList() {
    return Padding(
      padding: const EdgeInsets.fromLTRB(10, 10, 10, 70),
      child: ExpansionPanelList.radio(
        expansionCallback: (int index, bool isExpanded) {
          setState(() {
            playList[index].isExpanded = !isExpanded;
          });
        },
        children: playList.map<ExpansionPanel>((PlayListBean item) {
          return ExpansionPanelRadio(
              canTapOnHeader: true,
              headerBuilder: (BuildContext context, bool isExpanded) {
                return Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: ListTile(
                    leading: Image.network(item.imgUrl),
                    title: Text(
                      item.title,
                    ),
                  ),
                );
              },
              body: ListTile(
                title: Text(
                  item.desc,
                ),
                subtitle: Text(""),
                trailing: ElevatedButton(
                  onPressed: () => {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => MusicListPage(
                                item.id, item.title, item.imgUrl)))
                  },
                  child: Text("Start"),
                ),
              ),
              value: item.id);
        }).toList(),
      ),
    );
  }

  _getPlayList() async {
    var result = await MyDio().get(UrlRes.playlist + "?offset=$playListOffset");
    var data = PlaylistBean.fromJson(result);
    if (data.flag) {
      playListOffset = playListOffset + 15;
      data.list.forEach((temp) {
        setState(() {
          playList
              .add(PlayListBean(temp.id, temp.title, temp.desc, temp.imgUrl));
          _controller.finishLoad(success: true, noMore: false);
          _controller.finishRefresh(success: true);
        });
      });
    }
  }
}

class PlayListBean {
  int id;
  String title;
  String desc;
  String imgUrl;
  bool isExpanded;

  PlayListBean(this.id, this.title, this.desc, this.imgUrl,
      {this.isExpanded = false});
}
