import 'package:shared_preferences/shared_preferences.dart';

class UserData {
  void setToken(String? token) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('token', token!);
    print('token set: $token');
  }

  void deleteToken() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('token', "");
  }

  Future<String> getToken() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getString('token');
    return value ?? "";
  }

  void setUsername(String? username) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('username', username!);
    print('Username set: $username');
  }

  void deleteUsername() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('username', "");
  }

  Future<String> getUsername() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getString('username');
    return value ?? "";
  }

  void setUserId(int? id) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setInt('userId', id!);
    print('token set: $id');
  }

  void deleteUserId() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setInt('userId', -1);
  }

  Future<int> getUserId() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getInt('userId');
    return value ?? -1;
  }

  void setUserExpireDate(int? expireDate) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setInt('expire_date', expireDate!);
    print('token set: $expireDate');
  }

  void deleteUserExpireDate() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setInt('expire_date', -1);
  }

  Future<int> getUserExpireDate() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getInt('expire_date');
    return value ?? -1;
  }

  void setUserEmail(String? email) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('email', email!);
    print('Username set: $email');
  }

  void deleteUserEmail() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('email', "");
  }

  Future<String> getUserEmail() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getString('email');
    return value ?? "";
  }

  void setUserPhone(String? phone) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('phone', phone!);
    print('Username set: $phone');
  }

  void deleteUserPhone() async {
    print('delete token called');
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('phone', "");
  }

  Future<String> getUserPhone() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getString('phone');
    return value ?? "";
  }

  void setUserOnboardingSkipped(bool value) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setBool("onboarding", value);
  }

  Future<bool> getUserOnboardingSkipped() async {
    final prefs = await SharedPreferences.getInstance();
    final value = prefs.getBool("onboarding");
    return value ?? false;
  }

  // void deleteAllUserData() async {
  //   final prefs = await SharedPreferences.getInstance();
  //   await prefs.setString('token', "");
  //   await prefs.setString('phone', "");
  //   await prefs.setString('email', "");
  //   await prefs.setInt('expire_date', -1);
  //   await prefs.setInt('userId', -1);
  //   await prefs.setString('username', "");
  // }

  // void setAllUserDatas(String? token, int? expireDate, Customer? user) async {
  //   final prefs = await SharedPreferences.getInstance();
  //   await prefs.setString('token', token!);
  //   await prefs.setString('phone', user!.phone!);
  //   await prefs.setString('email', user.email!);
  //   await prefs.setInt('expire_date', expireDate!);
  //   await prefs.setInt('userId', user.id!);
  //   await prefs.setString('username', user.fullName!);
  // }

  // Future<Customer> getUserModel() async {
  //   final prefs = await SharedPreferences.getInstance();
  //   Customer user = Customer(
  //       id: prefs.getInt('userId'),
  //       email: prefs.getString('email'),
  //       phone: prefs.getString('phone'),
  //       fullName: prefs.getString('username'));

  //   return user;
  // }
}
