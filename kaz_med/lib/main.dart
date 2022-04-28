// import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'app/main/app.dart';
import 'app/main/app_model.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  /// Set device orientation
  await SystemChrome.setPreferredOrientations([DeviceOrientation.portraitUp]);

  final model = MyAppModel();
  await model.init();

  runApp(
    MyApp(model: model),
    // EasyLocalization(
    //     supportedLocales: const [
    //       Locale('en', 'EN'),
    //       Locale('ru', 'RU'),
    //       Locale('kk', 'KK'),
    //     ],
    //     path: 'assets/lang', // <-- change the path of the translation files
    //     fallbackLocale: const Locale('en', 'EN'),
    //     child: MyApp(
    //       model: model,
    //     )),
  );
}
