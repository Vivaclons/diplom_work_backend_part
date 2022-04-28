import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:kaz_med/base/base_provider.dart';
import 'package:kaz_med/pages/booking/provider/booking_provider.dart';
import 'package:kaz_med/shared/size_config.dart';
import 'package:kaz_med/shared/theme.dart';
import 'package:kaz_med/widgets/default_button.dart';
import 'package:kaz_med/widgets/default_text.dart';
import 'package:syncfusion_flutter_datepicker/datepicker.dart';

class BookingPage extends StatelessWidget {
  const BookingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseProvider<BookingPageProvider>(
      onReady: (p0) => p0.init(context),
      model: BookingPageProvider(),
      builder: (context, model, child) {
        return Scaffold(
          appBar: PreferredSize(
            preferredSize: const Size.fromHeight(70),
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
                text: 'Booking',
                fontSize: 18,
                fontWeight: FontWeight.w700,
              ),
              centerTitle: true,
              backgroundColor: AppColors.defaultBackgroundColor,
              elevation: 0,
            ),
          ),
          body: Padding(
            padding: EdgeInsets.symmetric(
              horizontal: getProportionateScreenWidth(25),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    DropdownButton(
                      value: model.month,
                      elevation: 0,
                      style: GoogleFonts.poppins(
                        textStyle: TextStyle(
                          color: AppColors.systemBlackColor,
                          fontWeight: FontWeight.w600,
                          fontSize: getProportionateScreenHeight(15),
                        ),
                      ),
                      icon: const Icon(
                        CupertinoIcons.chevron_down,
                        color: AppColors.systemBlackColor,
                        size: 16,
                      ),
                      underline: const SizedBox(),
                      onChanged: (String? newValue) =>
                          model.setMonth(newValue!),
                      items: model.months
                          .map<DropdownMenuItem<String>>(
                            (String value) => DropdownMenuItem<String>(
                              value: value,
                              child: DefaultText(
                                text: value,
                              ),
                            ),
                          )
                          .toList(),
                    ),
                    Row(
                      children: [
                        _buildNavigationControllers(
                          CupertinoIcons.back,
                          () => model.prevWeek(),
                        ),
                        SizedBox(
                          width: getProportionateScreenWidth(23),
                        ),
                        _buildNavigationControllers(
                          CupertinoIcons.forward,
                          () => model.nextWeek(),
                        ),
                      ],
                    ),
                  ],
                ),
                SizedBox(
                  height: getProportionateScreenHeight(20),
                ),
                SizedBox(
                  height: getProportionateScreenHeight(60),
                  child: SfDateRangePicker(
                    navigationMode: DateRangePickerNavigationMode.none,
                    controller: model.dateRangePickerController,
                    allowViewNavigation: false,
                    showNavigationArrow: true,
                    headerHeight: 0,
                    selectionMode: DateRangePickerSelectionMode.single,
                    selectionShape: DateRangePickerSelectionShape.rectangle,
                    monthViewSettings: DateRangePickerMonthViewSettings(
                      numberOfWeeksInView: 1,
                      viewHeaderHeight: 0,
                      viewHeaderStyle: DateRangePickerViewHeaderStyle(
                        textStyle: GoogleFonts.poppins(
                          textStyle: TextStyle(
                            color: AppColors.systemBlackColor,
                            fontSize: getProportionateScreenHeight(12),
                            fontWeight: FontWeight.w500,
                          ),
                        ),
                      ),
                    ),
                    selectionTextStyle: const TextStyle(
                      color: AppColors.systemBlackColor,
                    ),
                    selectionColor: AppColors.primaryColor.withOpacity(0),
                    onSelectionChanged: (args) {
                      log(args.value.toString());
                      model.selectDate(args.value);
                    },
                    cellBuilder: (context, details) {
                      return Container(
                        width: details.bounds.width,
                        height: details.bounds.height,
                        padding: EdgeInsets.symmetric(
                          vertical: getProportionateScreenHeight(7),
                        ),
                        margin: EdgeInsets.symmetric(
                          horizontal: getProportionateScreenWidth(4),
                        ),
                        decoration: BoxDecoration(
                          color: details.date == model.selectedDate
                              ? AppColors.primaryColor.withOpacity(0.12)
                              : null,
                          borderRadius: BorderRadius.circular(8),
                          border: Border.all(
                            color: AppColors.greyColor,
                          ),
                        ),
                        child: Column(
                          children: [
                            DefaultText(
                              text: _switchWeekDay(details.date.weekday),
                              fontSize: 12,
                              fontWeight: FontWeight.w500,
                              color: AppColors.greyColor,
                            ),
                            DefaultText(
                              text: details.date.day.toString(),
                              fontSize: 14,
                              fontWeight: FontWeight.w600,
                            ),
                          ],
                        ),
                      );
                    },
                  ),
                ),
                const Spacer(),
                DefaultText(
                  text: 'Select time',
                  fontSize: 15,
                  fontWeight: FontWeight.w600,
                ),
                SizedBox(
                  height: getProportionateScreenHeight(20),
                ),
                Expanded(
                  flex: 6,
                  child: GridView.builder(
                    physics: const NeverScrollableScrollPhysics(),
                    gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
                      maxCrossAxisExtent: getProportionateScreenWidth(140),
                      childAspectRatio: 2.5,
                      crossAxisSpacing: getProportionateScreenWidth(20),
                      mainAxisSpacing: getProportionateScreenHeight(20),
                    ),
                    itemCount: 15,
                    itemBuilder: (_, index) => GestureDetector(
                      onTap: () => model.selectTime(index),
                      child: Container(
                        padding: EdgeInsets.symmetric(
                          horizontal: getProportionateScreenWidth(22),
                          vertical: getProportionateScreenHeight(9),
                        ),
                        decoration: BoxDecoration(
                          color: model.isSelectedTimes[index]
                              ? AppColors.primaryColor.withOpacity(0.12)
                              : null,
                          borderRadius: BorderRadius.circular(4),
                          border: Border.all(
                            color: model.isSelectedTimes[index]
                                ? AppColors.primaryColor.withOpacity(0)
                                : AppColors.greyColor,
                          ),
                        ),
                        child: DefaultText(
                          text: model.times[index],
                          color: index == 1 || index == 3 || index == 8
                              ? AppColors.greyColor
                              : AppColors.systemBlackColor,
                          fontSize: 12,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                  ),
                ),
                const Spacer(),
                DefaultButton(
                  text: 'Next',
                  press: () => model.isButtonEnabled ? log('Ok') : log('Error'),
                  isButtonEnabled: model.isButtonEnabled,
                ),
                SizedBox(
                  height: getProportionateScreenHeight(28),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  _buildNavigationControllers(IconData icon, Function() onTap) {
    return GestureDetector(
      onTap: () => onTap(),
      child: Container(
        width: getProportionateScreenWidth(34),
        height: getProportionateScreenHeight(34),
        alignment: Alignment.center,
        decoration: BoxDecoration(
          color: AppColors.primaryColor.withOpacity(0.12),
          borderRadius: BorderRadius.circular(212),
        ),
        child: Icon(
          icon,
          color: AppColors.systemBlackColor,
          size: 18,
        ),
      ),
    );
  }

  _switchWeekDay(int value) {
    switch (value) {
      case 1:
        return 'M';
      case 2:
        return 'T';
      case 3:
        return 'W';
      case 4:
        return 'T';
      case 5:
        return 'F';
      case 6:
        return 'S';
      case 7:
        return 'S';
    }
  }
}
