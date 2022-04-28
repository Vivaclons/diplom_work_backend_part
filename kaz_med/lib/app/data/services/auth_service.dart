import 'dart:convert';
import 'package:http/http.dart' as http;

class AuthService {
  // Future<Result<dynamic, NetworkError>> login(
  //     String name, String password) async {
  //   return NetworkExecuter.execute(
  //     route: PlaceholderClient.login(name, password),
  //   );
  // }

  Future<http.Response> login(String name, String password) {
    return http.post(
      Uri.parse(
        'https://144a-46-34-146-223.ngrok.io/auth-service/auth',
      ),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(
        <String, String>{"username": name, "password": password},
      ),
    );
  }

  Future<http.Response> register(
      String firstName, String lastName, String email, String password) {
    return http.post(
      Uri.parse(
        'https://144a-46-34-146-223.ngrok.io/auth-service/registration/add-customer',
      ),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(
        <String, dynamic>{
          "customerName": firstName,
          "customerSurname": lastName,
          "customerTelNumber": "",
          "email": email,
          "username": firstName,
          "password": password,
          "address": "st. arre",
          "roles": ["ADMIN"]
        },
      ),
    );
  }
}
