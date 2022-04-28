import 'package:flutter/material.dart';

import '../shared/size_config.dart';
import '../shared/theme.dart';
import '../shared/ui_helper.dart';

class CustomAppBar extends PreferredSize {
  final String title;
  final bool isWithBackButton;

  CustomAppBar({
    Key? key,
    required this.title,
    this.isWithBackButton = false,
  }) : super(
          key: key,
          child: Container(),
          preferredSize: Size.fromHeight(
            getProportionateScreenHeight(200),
          ),
        );

  @override
  Widget build(BuildContext context) {
    return Container(
      height: preferredSize.height,
      padding: EdgeInsets.only(
        top: getProportionateScreenHeight(60),
      ),
      alignment: Alignment.center,
      decoration: const BoxDecoration(
        color: AppColors.primaryColor,
      ),
      
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          UIHelper.horizontalSpace(40),
          isWithBackButton
              ? InkWell(
                  onTap: () => Navigator.pop(context),
                  child: Padding(
                    padding: EdgeInsets.symmetric(
                      horizontal: getProportionateScreenWidth(10),
                      vertical: getProportionateScreenHeight(10),
                    ),
                    child: InkWell(
                      child: const Icon(
                        Icons.arrow_back_ios,
                        color: AppColors.whiteColor,
                      ),
                      onTap: () => Navigator.pop(context),
                    ),
                  ),
                )
              : const Text(''),
          const Spacer(),
          Text(
            title,
            style: TextStyle(
              color: AppColors.whiteColor,
              fontSize: getProportionateScreenWidth(36),
            ),
          ),
          const Spacer(),
          UIHelper.horizontalSpace(50),
        ],
      ),
    );
  }
}
