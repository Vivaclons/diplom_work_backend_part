import 'package:flutter/material.dart';
import 'package:kaz_med/widgets/default_text.dart';

import '../shared/size_config.dart';
import '../shared/theme.dart';

class DefaultButton extends StatelessWidget {
  const DefaultButton(
      {this.text,
      this.press,
      this.color = AppColors.primaryColor,
      this.textColor = AppColors.whiteColor,
      this.width = double.maxFinite,
      this.isButtonEnabled = true});
  final String? text;
  final Function? press;
  final Color color;
  final Color textColor;
  final double width;
  final bool isButtonEnabled;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: width,
      height: 50,
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(4),
          ),
          shadowColor: AppColors.primaryColor.withOpacity(0),
          primary: isButtonEnabled ? color : color.withOpacity(0.5),
        ),
        onPressed: press as void Function()?,
        child: DefaultText(
          text: text!,
          color: textColor,
        ),
      ),
    );
  }
}
