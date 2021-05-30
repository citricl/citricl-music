import 'package:dio/adapter.dart';
import 'package:dio/dio.dart';

class MyDio {
  Dio dio = Dio();

  Future<Map<String, dynamic>> post(
      Map<String, dynamic> data, String url) async {
    (dio.httpClientAdapter as DefaultHttpClientAdapter).onHttpClientCreate =
        (client) {
      client.badCertificateCallback = (cert, host, port) {
        return true;
      };
    };

    Response response = await dio.post(url, data: data);
    return response.data;
  }

  Future<Map<String, dynamic>> get(String url) async {
    (dio.httpClientAdapter as DefaultHttpClientAdapter).onHttpClientCreate =
        (client) {
      client.badCertificateCallback = (cert, host, port) {
        return true;
      };
    };

    Response response = await dio.get(url);
    // print("response = $response");
    return response.data;
  }
}
