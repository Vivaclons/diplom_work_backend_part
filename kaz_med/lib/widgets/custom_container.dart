import 'package:flutter/material.dart';

import '../shared/theme.dart';

class CustomContainer extends StatelessWidget {
  final Widget? child;
  final double? height;
  CustomContainer({this.child, this.height});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      alignment: Alignment.centerLeft,
      height: height,
      child: child,
      padding: const EdgeInsets.only(
        left: 10,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
        color: AppColors.primaryColor.withOpacity(0.05),
        // color: Colors.red,
      ),
    );
  }
}
