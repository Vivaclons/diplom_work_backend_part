import 'package:flutter/material.dart';
import '../../../app/main/navigator_state.dart';
import '../../../app/main/user_data.dart';
import '../../../shared/utils.dart';
import '../../freezed/network_error.dart';
import '../../freezed/result.dart';
import '../network_options/network_options.dart';
import 'network_decoder.dart';
import 'package:dio/dio.dart';
import '../layers/network_connectivity.dart';
import 'network_creator.dart';
import '../interfaces/base_client_generator.dart';
import '../interfaces/base_network_model.dart';

class NetworkExecuter {
  static UserData _userData = UserData();
  static bool debugMode = true;

  static showAlert(BuildContext? context, DioError error) {
    return showDialogCustom(
      context: context!,
      barrierDismissible: true,
      backgroundColor: Colors.black26,
      title: 'Ошибка',
      child: Container(
        padding: EdgeInsets.all(20),
        child: Center(
          child: Text(
            "${error.response!.data["message"]}",
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }

  static void handleError(DioError error, BuildContext buildContext) {
    print(error.message);
    switch (error.response!.statusCode) {
      case 402:
        print("402");
        break;
      case 403:
        showAlert(GlobalVariable.navState.currentContext!, error);
        break;
      case 422:
        // showAlert(GlobalVariable.navState.currentContext!, error);
        print("The data you have provided is invalid.");
        break;
      case 401:
        // _userData.deleteToken();
        // _userData.deletePincode();
        // _userData.deleteUserId();
        // _userData.deleteUsername();
        // Navigator.pushAndRemoveUntil(
        //   GlobalVariable.navState.currentContext!,
        //   MaterialPageRoute(
        //     builder: (context) => LoginScreen(),
        //   ),
        //   (route) => false,
        // );
        // print("Unauthorized");
        break;
      case 404:
        // showAlert(GlobalVariable.navState.currentContext!, error);
        print("Request not found.");
        break;
      case 500:
        showDialogCustom(
          context: GlobalVariable.navState.currentContext!,
          barrierDismissible: true,
          backgroundColor: Colors.black26,
          title: 'Error',
          child: Container(
            padding: EdgeInsets.all(20),
            child: Center(
              child: Text(
                "Something went wrong!",
                textAlign: TextAlign.center,
              ),
            ),
          ),
        );
        print(
            "There is something wrong with our servers, please report to the admin so it gets fixed.");
        break;
      default:
        showDialogCustom(
          context: GlobalVariable.navState.currentContext!,
          barrierDismissible: true,
          backgroundColor: Colors.black26,
          title: 'Ошибка',
          child: Container(
            padding: EdgeInsets.all(20),
            child: Center(
              child: Text(
                "Что то пошло не так!",
                textAlign: TextAlign.center,
              ),
            ),
          ),
        );
        print("Something went wrong.");
    }
  }

  static Future<Result<K, NetworkError>> execute<T extends BaseNetworkModel, K>(
      {required BaseClientGenerator route,
      T? responseType,
      NetworkOptions? options}) async {
    if (debugMode) print(route);

    // Check Network Connectivity
    if (await NetworkConnectivity.status) {
      try {
        var response =
            await NetworkCreator.shared.request(route: route, options: options);
        if (responseType != null) {
          var data = NetworkDecoder.shared
              .decode<T, K>(response: response, responseType: responseType);
          return Result.success(data);
        } else {
          return Result.success('' as K);
        }

        // NETWORK ERROR
      } on DioError catch (diorError) {
        if (debugMode)
          print("$route => ${NetworkError.request(error: diorError)}");
        handleError(diorError, GlobalVariable.navState.currentContext!);
        return Result.failure(NetworkError.request(error: diorError));

        // TYPE ERROR
      } on TypeError catch (e) {
        if (debugMode)
          print("$route => ${NetworkError.type(error: e.toString())}");
        return Result.failure(NetworkError.type(error: e.toString()));
      }

      // No Internet Connection
    } else {
      if (debugMode)
        print(NetworkError.connectivity(message: 'No Internet Connection'));
      showCustomSnackBar(
          context: GlobalVariable.navState.currentContext!,
          message: 'Интернет подключение отсутсвует');
      return Result.failure(
          NetworkError.connectivity(message: 'No Internet Connection'));
    }
  }
}
