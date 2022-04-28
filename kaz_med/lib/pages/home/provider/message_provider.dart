import 'package:flutter/cupertino.dart';
import 'package:kaz_med/base/base_bloc.dart';
import 'package:kaz_med/shared/size_config.dart';

class MessageProvider extends BaseBloc {
  final GlobalKey formKey = GlobalKey<FormState>();
  TextEditingController nameController = TextEditingController();
  TextEditingController titleController = TextEditingController();
  TextEditingController messageController = TextEditingController();

  init(BuildContext context) {
    setLoading(true);
    SizeConfig().init(context);
    setLoading(false);
  }
}
