import 'package:kaz_med/app/main/user_data.dart';
import 'package:kaz_med/core/network/interfaces/base_client_generator.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

// part 'json_api_client_freezed.dart';
part 'json_api_client.freezed.dart';

@freezed
class PlaceholderClient extends BaseClientGenerator with _$PlaceholderClient {
  static final UserData _userData = UserData();
  // Routes
  const PlaceholderClient._() : super();
  const factory PlaceholderClient.login(String name, String password) = _Login;

  @override
  String get baseURL => 'https://144a-46-34-146-223.ngrok.io/';

  @override
  dynamic get body {
    return this.maybeWhen(
        orElse: () {
          return null;
        },
        login: (String name, String password) =>
            {"username": name, "password": password});
  }

  @override
  Future<Map<String, dynamic>> get header async {
    return {
      'Content-Type': 'application/json',
      "authorization": "Bearer " + await _userData.getToken(),
    };
  }

  @override
  String get method {
    return this.maybeWhen<String>(
      orElse: () => 'GET',
      login: (String name, String password) => 'POST',
    );
  }

  @override
  String get path {
    return this.when<String>(
      login: (String name, String password) => 'auth-service/auth',
    );
  }

  @override
  Map<String, dynamic>? get queryParameters {
    return this.maybeWhen(
      orElse: () => null,
    );
  }
}
