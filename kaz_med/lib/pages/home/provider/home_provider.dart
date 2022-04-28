import 'package:flutter/cupertino.dart';
import 'package:kaz_med/base/base_bloc.dart';
import 'package:kaz_med/shared/size_config.dart';

class HomeProvider extends BaseBloc {
  List<bool> sectionsToggles = List.generate(4, (index) => true);

  init(BuildContext context) {
    setLoading(true);
    SizeConfig().init(context);
    setLoading(false);
  }

  toggleSections(int index) {
    sectionsToggles[index] = !sectionsToggles[index];
    notifyListeners();
  }
}
