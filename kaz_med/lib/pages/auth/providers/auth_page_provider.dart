import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kaz_med/base/base_bloc.dart';
import 'package:kaz_med/pages/auth/ui/login_page.dart';
import 'package:kaz_med/pages/auth/ui/signup_page.dart';
import 'package:kaz_med/shared/size_config.dart';

class AuthPageProvider extends BaseBloc {
  init(BuildContext context) {
    setLoading(true);
    SizeConfig().init(context);
    setLoading(false);
  }

  toLoginPage(context) {
    Navigator.pushAndRemoveUntil(
        context,
        MaterialPageRoute(builder: (context) => const LoginPage()),
        (route) => false);
  }

  toSignUpPage(context) {
    Navigator.pushAndRemoveUntil(
        context,
        MaterialPageRoute(builder: (context) => const SignUpPage()),
        (route) => false);
  }
}
