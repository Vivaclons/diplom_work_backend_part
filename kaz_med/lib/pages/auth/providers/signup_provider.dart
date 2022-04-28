import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kaz_med/app/data/services/auth_service.dart';
import 'package:kaz_med/base/base_bloc.dart';
import 'package:kaz_med/pages/auth/ui/login_page.dart';
import 'package:kaz_med/shared/size_config.dart';

import '../../../app/main/navigator_state.dart';
import '../../../shared/utils.dart';

class SignUpProvider extends BaseBloc {
  TextEditingController firstNameController = TextEditingController();
  TextEditingController lastNameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  bool isPasswordVisible = true;

  AuthService _authService = AuthService();

  init(BuildContext context) {
    setLoading(true);
    SizeConfig().init(context);
    setLoading(false);
  }

  setPasswordVisibility() {
    isPasswordVisible = !isPasswordVisible;
    notifyListeners();
  }

  register(context) async {
    setLoading(true);
    try {
      var response = await _authService.register(
          firstNameController.text,
          lastNameController.text,
          emailController.text,
          passwordController.text);
      if (response.statusCode == 200) {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (_) => LoginPage(),
          ),
        );
      } else {
        showDialogCustom(
        context: GlobalVariable.navState.currentContext!,
        barrierDismissible: true,
        backgroundColor: Colors.black26,
        title: 'Error',
        child: Container(
          padding: const EdgeInsets.all(20),
          child: const Center(
            child: Text(
              'Invalid inputs',
              textAlign: TextAlign.center,
            ),
          ),
        ),
      );
      }
    } catch (e) {
      return showDialogCustom(
        context: GlobalVariable.navState.currentContext!,
        barrierDismissible: true,
        backgroundColor: Colors.black26,
        title: 'Error',
        child: Container(
          padding: const EdgeInsets.all(20),
          child: Center(
            child: Text(
              e.toString(),
              textAlign: TextAlign.center,
            ),
          ),
        ),
      );
    }
    setLoading(false);
  }
}
