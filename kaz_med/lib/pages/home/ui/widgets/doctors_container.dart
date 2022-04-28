import 'package:flutter/cupertino.dart';
import 'package:flutter_svg/svg.dart';

import '../../../../shared/size_config.dart';
import '../../../../shared/theme.dart';
import '../../../../widgets/default_text.dart';

class DoctorsContainer extends StatelessWidget {
  const DoctorsContainer({Key? key, required this.image}) : super(key: key);
  final String image;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(
        horizontal: getProportionateScreenWidth(7),
        vertical: getProportionateScreenHeight(7),
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        color: AppColors.whiteColor,
        boxShadow: [
          BoxShadow(
            offset: const Offset(2, 4),
            color: AppColors.shadowColor,
            blurRadius: 1,
          ),
        ],
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Image.asset(image),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              DefaultText(
                isCenter: false,
                text: 'Akhmetova A.A.',
                fontSize: 16,
                fontWeight: FontWeight.w600,
              ),
              SizedBox(
                height: getProportionateScreenHeight(9),
              ),
              DefaultText(
                isCenter: false,
                text: 'Cardiologist',
                fontWeight: FontWeight.w500,
                color: AppColors.greyColor,
              ),
              SizedBox(
                height: getProportionateScreenHeight(4),
              ),
              Row(
                children: [
                  const Icon(
                    CupertinoIcons.location,
                    color: AppColors.greyColor,
                    size: 12,
                  ),
                  SizedBox(
                    width: getProportionateScreenWidth(10),
                  ),
                  DefaultText(
                    text: '4km away',
                    fontSize: 12,
                    fontWeight: FontWeight.w400,
                    color: AppColors.greyColor,
                  )
                ],
              ),
              SizedBox(
                height: getProportionateScreenHeight(4),
              ),
              Container(
                padding: EdgeInsets.symmetric(
                  horizontal: getProportionateScreenWidth(4),
                  vertical: getProportionateScreenHeight(4),
                ),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(4),
                  color: AppColors.primaryColor.withOpacity(0.26),
                ),
                child: Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const Icon(
                      CupertinoIcons.star_fill,
                      color: AppColors.primaryColor,
                      size: 12,
                    ),
                    SizedBox(
                      width: getProportionateScreenWidth(5),
                    ),
                    DefaultText(
                      text: '4.7',
                      fontSize: 10,
                      fontWeight: FontWeight.w500,
                      color: AppColors.primaryColor,
                    ),
                  ],
                ),
              ),
            ],
          ),
          const SizedBox(
            width: 1,
          ),
          Container(
            padding: EdgeInsets.symmetric(
              horizontal: getProportionateScreenWidth(7),
              vertical: getProportionateScreenHeight(7),
            ),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(4),
              color: AppColors.primaryColor.withOpacity(0.12),
            ),
            child: SvgPicture.asset(
              AppSvgImages.heart,
              color: AppColors.primaryColor,
            ),
          ),
        ],
      ),
    );
  }
}
