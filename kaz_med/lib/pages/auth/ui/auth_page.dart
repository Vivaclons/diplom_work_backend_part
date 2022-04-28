import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:kaz_med/base/base_provider.dart';
import 'package:kaz_med/pages/auth/providers/auth_page_provider.dart';
import 'package:kaz_med/pages/auth/ui/login_page.dart';
import 'package:kaz_med/shared/size_config.dart';
import 'package:kaz_med/shared/theme.dart';
import 'package:kaz_med/widgets/default_button.dart';
import 'package:kaz_med/widgets/default_text.dart';
import 'package:kaz_med/widgets/loading_view.dart';

class AuthPage extends StatelessWidget {
  const AuthPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseProvider<AuthPageProvider>(
      onReady: (p0) => p0.init(context),
      model: AuthPageProvider(),
      builder: (context, model, child) {
        return model.isLoading
            ? const LoadingView()
            : Scaffold(
                body: Stack(
                  children: [
                    Positioned(
                      child: Image.asset(AppPngImages.auth_page_image),
                    ),
                    Positioned(
                      child: Container(
                        decoration: BoxDecoration(
                          // image: DecorationImage(
                          //   image: AssetImage(AppPngImages.auth_page_image),
                          // ),
                          gradient: LinearGradient(
                            begin: Alignment.topCenter,
                            end: Alignment.bottomCenter,
                            colors: [
                              AppColors.primaryColor.withOpacity(0.26),
                              AppColors.primaryColor,
                              AppColors.primaryColor,
                            ],
                          ),
                        ),
                      ),
                    ),
                    Positioned(
                      child: Center(
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            SvgPicture.asset(AppSvgImages.heartRate),
                            DefaultText(
                              text: 'KazMed',
                              fontWeight: FontWeight.w700,
                              fontSize: 24,
                              color: AppColors.whiteColor,
                            ),
                          ],
                        ),
                      ),
                    ),
                    Positioned(
                      child: Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: getProportionateScreenWidth(25),
                        ),
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            const Spacer(),
                            GestureDetector(
                              onTap: (() {
                                model.toLoginPage(context);
                              }),
                              child: _buildButtons(
                                'Login',
                              ),
                            ),
                            // _buildButtons('Login', model.toLoginPage(context)),
                            SizedBox(
                              height: getProportionateScreenHeight(17),
                            ),
                            GestureDetector(
                              onTap: (() {
                                model.toSignUpPage(context);
                              }),
                              child: _buildButtons(
                                'Sign Up',
                                isFilled: true,
                              ),
                            ),
                            SizedBox(
                              height: getProportionateScreenHeight(58),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              );
      },
    );
  }

  _buildButtons(String text, {bool isFilled = false}) {
    return Container(
      width: double.maxFinite,
      padding: EdgeInsets.symmetric(
        vertical: getProportionateScreenHeight(12),
      ),
      decoration: BoxDecoration(
        color: isFilled ? AppColors.whiteColor : null,
        borderRadius: BorderRadius.circular(4),
        border: Border.all(color: AppColors.whiteColor),
      ),
      child: DefaultText(
        text: text,
        fontWeight: FontWeight.w500,
        color: isFilled ? AppColors.primaryColor : AppColors.whiteColor,
      ),
    );
  }
}
