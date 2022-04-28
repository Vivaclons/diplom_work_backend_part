import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:kaz_med/base/base_provider.dart';
import 'package:kaz_med/pages/home/provider/message_provider.dart';
import 'package:kaz_med/pages/home/ui/widgets/success_message.dart';
import 'package:kaz_med/shared/size_config.dart';
import 'package:kaz_med/shared/theme.dart';
import 'package:kaz_med/widgets/default_button.dart';
import 'package:kaz_med/widgets/default_text.dart';

class MessagePage extends StatelessWidget {
  const MessagePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseProvider<MessageProvider>(
      onReady: (p0) => p0.init(context),
      model: MessageProvider(),
      builder: (context, model, child) {
        return GestureDetector(
          onTap: () => FocusScope.of(context).unfocus(),
          child: Scaffold(
            resizeToAvoidBottomInset: false,
            appBar: PreferredSize(
              preferredSize: Size.fromHeight(
                getProportionateScreenHeight(70),
              ),
              child: AppBar(
                automaticallyImplyLeading: false,
                leading: InkWell(
                  onTap: () => Navigator.pop(context),
                  child: const Icon(
                    Icons.arrow_back_ios,
                    color: AppColors.systemBlackColor,
                  ),
                ),
                title: DefaultText(
                  text: 'MessagePage',
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
              child: Form(
                key: model.formKey,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _buildFormName('Name'),
                    SizedBox(
                      height: getProportionateScreenHeight(10),
                    ),
                    _buildTextFormField(model.nameController),
                    const Spacer(),
                    _buildFormName('Title of concern'),
                    SizedBox(
                      height: getProportionateScreenHeight(10),
                    ),
                    _buildTextFormField(model.titleController),
                    const Spacer(),
                    _buildFormName('Message'),
                    SizedBox(
                      height: getProportionateScreenHeight(10),
                    ),
                    _buildTextFormField(
                      model.messageController,
                      multiline: true,
                    ),
                    const Spacer(
                      flex: 5,
                    ),
                    DefaultButton(
                      text: 'Send message',
                      press: () => showCupertinoDialog(
                        context: context,
                        barrierDismissible: true,
                        builder: (_) => const AlertDialog(
                          insetPadding:
                              EdgeInsets.symmetric(horizontal: 0, vertical: 0),
                          content: SuccessMessage(),
                        ),
                      ),
                    ),
                    SizedBox(
                      height: getProportionateScreenHeight(26),
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }

  _buildFormName(String name) {
    return DefaultText(
      text: name,
      fontSize: 12,
      fontWeight: FontWeight.w500,
    );
  }

  _buildTextFormField(
    TextEditingController controller, {
    bool multiline = false,
  }) {
    return TextFormField(
      controller: controller,
      cursorColor: AppColors.systemBlackColor,
      keyboardType: TextInputType.emailAddress,
      maxLines: multiline ? 7 : 1,
      style: GoogleFonts.poppins(
        textStyle: TextStyle(
          color: AppColors.systemBlackColor,
          fontWeight: FontWeight.w400,
          fontSize: getProportionateScreenHeight(14),
        ),
      ),
      decoration: InputDecoration(
        focusedBorder: const OutlineInputBorder(
          borderSide: BorderSide(
            color: AppColors.greyColor,
          ),
        ),
        border: OutlineInputBorder(
          borderSide: const BorderSide(
            color: AppColors.greyColor,
          ),
          borderRadius: BorderRadius.circular(4),
        ),
      ),
    );
  }
}
