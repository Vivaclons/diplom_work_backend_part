import 'package:flutter/material.dart';
import 'package:kaz_med/shared/theme.dart';

class CustomActionDialog extends StatelessWidget {
  final Widget child;
  final BuildContext ctx;
  final bool isDismissible;
  final String? title;

  CustomActionDialog({
    required this.child,
    required this.isDismissible,
    required this.ctx,
    this.title = 'Нажмите для выбора',
  });

  @override
  Widget build(BuildContext contx) {
    return Builder(builder: (context) {
      return WillPopScope(
        onWillPop: () async => this.isDismissible ? true : false,
        child: Dialog(
          insetPadding: EdgeInsets.all(38),
          clipBehavior: Clip.antiAliasWithSaveLayer,
          insetAnimationDuration: const Duration(milliseconds: 500),
          insetAnimationCurve: Curves.bounceIn,
          shape: const RoundedRectangleBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(15),
            ),
          ),
          child: Container(
            decoration: const BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.all(
                Radius.circular(15),
              ),
            ),
            constraints:
                BoxConstraints(maxWidth: MediaQuery.of(context).size.width),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Container(
                  alignment: Alignment.topCenter,
                  padding: const EdgeInsets.only(left: 20),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Flexible(fit: FlexFit.loose, child: Text("$title")),
                      IconButton(
                        icon: const Icon(
                          Icons.close_rounded,
                          color: Colors.grey,
                        ),
                        onPressed: () =>
                            Navigator.of(context, rootNavigator: true).pop(),
                      ),
                    ],
                  ),
                ),
                Container(
                  color: AppColors.systemBlackColor,
                  height: 0.5,
                  width: MediaQuery.of(context).size.width,
                ),
                child,
              ],
            ),
          ),
        ),
      );
    });
  }
}
