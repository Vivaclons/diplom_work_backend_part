import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:kaz_med/base/base_provider.dart';
import 'package:kaz_med/pages/home/provider/home_provider.dart';
import 'package:kaz_med/pages/home/ui/doctors_profile_page.dart';
import 'package:kaz_med/pages/home/ui/widgets/doctors_container.dart';
import 'package:kaz_med/shared/size_config.dart';
import 'package:kaz_med/shared/theme.dart';
import 'package:kaz_med/widgets/default_text.dart';

class HomePage extends StatelessWidget {
  HomePage({Key? key}) : super(key: key);

  List<String> svgs = [
    AppSvgImages.cardiologist,
    AppSvgImages.dentist,
    AppSvgImages.optician,
    AppSvgImages.orthopedic,
  ];

  List<String> sections = [
    'Cardiologist',
    'Dentist',
    'Optician',
    'Optician',
  ];

  List<String> doctors = [
    AppPngImages.doctor_1,
    AppPngImages.doctor_2,
    AppPngImages.doctor_3,
    AppPngImages.doctor_4,
  ];

  @override
  Widget build(BuildContext context) {
    return BaseProvider<HomeProvider>(
      onReady: (p0) => p0.init(context),
      model: HomeProvider(),
      builder: (context, model, child) {
        return Scaffold(
          appBar: PreferredSize(
            preferredSize: Size.fromHeight(
              getProportionateScreenHeight(70),
            ),
            child: AppBar(
              title: DefaultText(
                text: 'Consultation',
                fontSize: 18,
                fontWeight: FontWeight.w700,
              ),
              centerTitle: true,
              backgroundColor: AppColors.defaultBackgroundColor,
              elevation: 0,
              actions: [
                IconButton(
                  onPressed: () {},
                  icon: SvgPicture.asset(
                    AppSvgImages.search,
                  ),
                ),
              ],
            ),
          ),
          body: Padding(
            padding: EdgeInsets.symmetric(
              horizontal: getProportionateScreenWidth(25),
            ),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: List.generate(
                    svgs.length,
                    (index) => GestureDetector(
                      onTap: () => model.toggleSections(index),
                      child: Column(
                        children: [
                          Container(
                            alignment: Alignment.center,
                            width: getProportionateScreenWidth(60),
                            height: getProportionateScreenHeight(60),
                            decoration: BoxDecoration(
                              color: model.sectionsToggles[index]
                                  ? AppColors.primaryColor
                                  : AppColors.whiteColor,
                              borderRadius: BorderRadius.circular(10),
                            ),
                            child: SvgPicture.asset(
                              svgs[index],
                              color: model.sectionsToggles[index]
                                  ? AppColors.whiteColor
                                  : AppColors.primaryColor,
                            ),
                          ),
                          SizedBox(
                            height: getProportionateScreenHeight(10),
                          ),
                          DefaultText(
                            text: sections[index],
                            fontSize: 10,
                            fontWeight: FontWeight.w500,
                          )
                        ],
                      ),
                    ),
                  ),
                ),
                SizedBox(
                  height: getProportionateScreenHeight(25),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    DefaultText(
                      text: 'Top Doctors',
                      fontSize: 15,
                      fontWeight: FontWeight.w600,
                    ),
                    TextButton(
                      onPressed: () {},
                      child: DefaultText(
                        text: 'View all',
                        color: AppColors.primaryColor,
                        fontSize: 12,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                  ],
                ),
                Expanded(
                  child: ListView.separated(
                    padding: EdgeInsets.only(
                      top: getProportionateScreenHeight(17),
                      bottom: getProportionateScreenHeight(17),
                    ),
                    shrinkWrap: true,
                    physics: const BouncingScrollPhysics(),
                    itemCount: doctors.length,
                    separatorBuilder: (_, index) => SizedBox(
                      height: getProportionateScreenHeight(28),
                    ),
                    itemBuilder: (_, index) => GestureDetector(
                      onTap: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (_) => DoctorsProfilePage(
                            image: doctors[index],
                          ),
                        ),
                      ),
                      child: DoctorsContainer(
                        image: doctors[index],
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
