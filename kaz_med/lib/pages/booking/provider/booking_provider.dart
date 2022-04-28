import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:kaz_med/base/base_bloc.dart';
import 'package:syncfusion_flutter_datepicker/datepicker.dart';

import '../../../shared/size_config.dart';

class BookingPageProvider extends BaseBloc {
  String month = '';
  List<String> months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
  ];
  DateTime now = DateTime.now();
  DateTime? selectedDate;
  DateRangePickerController dateRangePickerController =
      DateRangePickerController();

  List<String> times = [
    '9:00AM',
    '9:30AM',
    '10:00AM',
    '10:30AM',
    '11:00AM',
    '11:30AM',
    '1:00PM',
    '1:30PM',
    '2:00PM',
    '2:30PM',
    '3:00PM',
    '3:30PM',
    '4:00PM',
    '4:30PM',
    '5:00PM',
  ];
  List<bool> isSelectedTimes = List.generate(15, (index) => false);
  int? selectedTime;

  bool isButtonEnabled = false;

  init(BuildContext context) {
    setLoading(true);
    month = _switchNumberToMonth(now.month);
    SizeConfig().init(context);
    setLoading(false);
  }

  setMonth(String value) {
    month = value;
    dateRangePickerController.displayDate =
        DateTime(now.year, _switchMonthToNumber(value), 1);
    notifyListeners();
  }

  nextWeek() {
    dateRangePickerController.forward!();
    month = _switchNumberToMonth(dateRangePickerController.displayDate!.month);
    log(dateRangePickerController.displayDate!.month.toString());
    notifyListeners();
  }

  prevWeek() {
    dateRangePickerController.backward!();
    month = _switchNumberToMonth(dateRangePickerController.displayDate!.month);
    notifyListeners();
  }

  selectDate(DateTime date) {
    selectedDate = date;
    if (selectedDate != null && selectedTime != null) {
      isButtonEnabled = true;
    } else {
      isButtonEnabled = false;
    }
    notifyListeners();
  }

  selectTime(int index) {
    for (int buttonIndex = 0;
        buttonIndex < isSelectedTimes.length;
        buttonIndex++) {
      if (buttonIndex == index) {
        isSelectedTimes[buttonIndex] = true;
        selectedTime = buttonIndex;
      } else {
        isSelectedTimes[buttonIndex] = false;
      }
    }
    if (selectedDate != null && selectedTime != null) {
      isButtonEnabled = true;
    } else {
      isButtonEnabled = false;
    }
    notifyListeners();
  }

  _switchNumberToMonth(int value) {
    switch (value) {
      case 1:
        return months[0];
      case 2:
        return months[1];
      case 3:
        return months[2];
      case 4:
        return months[3];
      case 5:
        return months[4];
      case 6:
        return months[5];
      case 7:
        return months[6];
      case 8:
        return months[7];
      case 9:
        return months[8];
      case 10:
        return months[9];
      case 11:
        return months[10];
      case 12:
        return months[11];
    }
  }

  _switchMonthToNumber(String value) {
    switch (value) {
      case 'January':
        return 1;
      case 'February':
        return 2;
      case 'March':
        return 3;
      case 'April':
        return 4;
      case 'May':
        return 5;
      case 'June':
        return 6;
      case 'July':
        return 7;
      case 'August':
        return 8;
      case 'September':
        return 9;
      case 'October':
        return 10;
      case 'November':
        return 11;
      case 'December':
        return 12;
    }
  }
}
