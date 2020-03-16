package com.akalin.template.comment.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * 生成二维码工具类
 * Created by akalin on 2019/5/9 0009.
 */
public class CreateQRCodeUtil {
    public static void main(String[] args) {
        /** 二维码大小 */
        int width = 500;
        int height = 500;
        /** 二维码格式 */
        String format = "png";
        /** 二维码内容 */
        String content = "http://59.36.8.187:8070/OAPLUGIN/derucci/index/index";
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        /** 生成二维码 */
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File("F:/二维码.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
