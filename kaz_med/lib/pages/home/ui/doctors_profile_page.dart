import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:kaz_med/base/base_provider.dart';
import 'package:kaz_med/pages/booking/ui/booking_page.dart';
import 'package:kaz_med/pages/home/provider/doctors_profile_provider.dart';
import 'package:kaz_med/pages/home/ui/message_page.dart';
import 'package:kaz_med/pages/home/ui/widgets/doctors_container.dart';
import 'package:kaz_med/shared/size_config.dart';
import 'package:kaz_med/shared/theme.dart';
import 'package:kaz_med/widgets/default_button.dart';
import 'package:kaz_med/widgets/default_text.dart';

class DoctorsProfilePage extends StatelessWidget {
  DoctorsProfilePage({Key? key, required this.image}) : super(key: key);
  final String image;

  List<String> qualifiactions = [
    'Bachelor of Medicine, Bachelor of Surgery (MBBS), 1989',
    'Fellows of the Royal Australasian of Surgeons, 1999',
    'Advanced Diploma in Business Management, 2010',
  ];

  List<String> services = [
    'Surgery for coronary heart disease',
    'Combined cardiac procedures',
    'Diagnosis and treatment of heart conditions',
  ];

  @override
  Widget build(BuildContext context) {
    return BaseProvider<DoctorsProfileProvider>(
      onReady: (p0) => p0.init(context),
      model: DoctorsProfileProvider(),
      builder: (context, model, child) {
        return Scaffold(
          appBar: PreferredSize(
            preferredSize: Size.fromHeight(
              getProportionateScreenHeight(70),
            ),
            child: AppBar(
              automaticallyImplyLeading: false,
              leading: IconButton(
                onPressed: () => Navigator.pop(context),
                icon: const Icon(
                  Icons.arrow_back_ios,
                  color: AppColors.systemBlackColor,
                ),
              ),
              title: DefaultText(
                text: 'Doctor´s Profile',
                fontSize: 18,
                fontWeight: FontWeight.w700,
              ),
              centerTitle: true,
              backgroundColor: AppColors.defaultBackgroundColor,
              elevation: 0,
              actions: [
                IconButton(
                  onPressed: () {},
                  icon: const Icon(
                    CupertinoIcons.ellipsis_vertical,
                    color: AppColors.systemBlackColor,
                  ),
                )
              ],
            ),
          ),
          bottomSheet: _bottomSheet(context),
          body: Padding(
            padding: EdgeInsets.symmetric(
              horizontal: getProportionateScreenWidth(25),
            ),
            child: SingleChildScrollView(
              physics: const BouncingScrollPhysics(),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  DoctorsContainer(image: image),
                  SizedBox(
                    height: getProportionateScreenHeight(17),
                  ),
                  DefaultText(
                    text: 'About',
                    fontSize: 15,
                    fontWeight: FontWeight.w600,
                  ),
                  SizedBox(
                    height: getProportionateScreenHeight(10),
                  ),
                  DefaultText(
                    text:
                        'Akhmetova Aygerim was born and raised in Kazakhstan, and completed her medical degree at the Kazakh National Medical University  . She is fully trained in all aspects of cardiology and a founding member of Blitz & Hertz.',
                    isCenter: false,
                    fontSize: 12,
                    fontWeight: FontWeight.w400,
                    color: AppColors.greyColor,
                  ),
                  _divider(),
                  DefaultText(
                    text: 'Qualifications',
                    fontSize: 15,
                    fontWeight: FontWeight.w600,
                  ),
                  SizedBox(
                    height: getProportionateScreenHeight(10),
                  ),
                  _buildPointers(qualifiactions),
                  _divider(),
                  DefaultText(
                    text: 'Services',
                    fontSize: 15,
                    fontWeight: FontWeight.w600,
                  ),
                  SizedBox(
                    height: getProportionateScreenHeight(10),
                  ),
                  _buildPointers(services),
                  _divider(),
                  _buildOtherTiles('Reviews'),
                  _divider(),
                  _buildOtherTiles('Fees'),
                  SizedBox(
                    height: getProportionateScreenHeight(96),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  _bottomSheet(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(
        horizontal: getProportionateScreenWidth(25),
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Row(
            children: [
              GestureDetector(
                onTap: () => Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (_) => const MessagePage(),
                  ),
                ),
                child: Container(
                  color: AppColors.defaultBackgroundColor,
                  child: Container(
                    padding: EdgeInsets.symmetric(
                      horizontal: getProportionateScreenWidth(14),
                      vertical: getProportionateScreenHeight(14),
                    ),
                    decoration: BoxDecoration(
                      color: AppColors.primaryColor.withOpacity(0.12),
                      borderRadius: BorderRadius.circular(8),
                    ),
                    child: SvgPicture.asset(AppSvgImages.message),
                  ),
                ),
              ),
              SizedBox(
                width: getProportionateScreenWidth(30),
              ),
              Expanded(
                child: DefaultButton(
                  text: 'Book appointment',
                  textColor: AppColors.whiteColor,
                  press: () => Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (_) => BookingPage(),
                    ),
                  ),
                ),
              ),
            ],
          ),
          SizedBox(
            height: getProportionateScreenHeight(26),
          ),
        ],
      ),
    );
  }

  _divider() {
    return Padding(
      padding: EdgeInsets.symmetric(
        vertical: getProportionateScreenHeight(13),
      ),
      child: const Divider(
        color: AppColors.greyColor,
      ),
    );
  }

  _buildPointers(List<String> list) {
    return Column(
      children: List.generate(
        list.length,
        (index) => Row(
          children: [
            Container(
              width: getProportionateScreenWidth(5),
              height: getProportionateScreenHeight(5),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: AppColors.systemBlackColor,
              ),
            ),
            SizedBox(
              width: getProportionateScreenWidth(15),
            ),
            Expanded(
              child: DefaultText(
                text: list[index],
                isCenter: false,
                fontSize: 12,
                fontWeight: FontWeight.w400,
                color: AppColors.greyColor,
              ),
            ),
          ],
        ),
      ),
    );
  }

  _buildOtherTiles(String text) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        DefaultText(
          text: text,
          fontSize: 15,
          fontWeight: FontWeight.w600,
        ),
        const Icon(
          Icons.arrow_forward_ios,
          color: AppColors.systemBlackColor,
        ),
      ],
    );
  }
}
