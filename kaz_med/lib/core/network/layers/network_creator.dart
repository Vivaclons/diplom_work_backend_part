import 'dart:developer';
import 'dart:io';
import 'package:dio/dio.dart';
import '../interfaces/base_client_generator.dart';
import '../network_options/network_options.dart';

class NetworkCreator {
  static var shared = NetworkCreator();

  Dio _client = Dio();
  // final _client = RetryClient(http.Client());

  Future<Response> request(
      {required BaseClientGenerator route, NetworkOptions? options}) async {
    // log('header: ${await route.header}');
    return _client.fetch(
      RequestOptions(
        baseUrl: route.baseURL,
        method: route.method,
        path: route.path,
        headers: await route.header,
        queryParameters: route.queryParameters,
        data: route.body,
        sendTimeout: route.sendTimeout,
        receiveTimeout: route.sendTimeout,
        onReceiveProgress: options?.onReceiveProgress,
        validateStatus: (statusCode) => (statusCode! >= HttpStatus.ok &&
            statusCode <= HttpStatus.multipleChoices),
      ),
    );
  }
}
