package com.csg.demo.dahua.lib;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Common JNA helper methods used by the NetSDK bindings.
 */
public final class ToolKits {

    private ToolKits() {
    }

    public static void GetPointerData(Pointer pNativeData, Structure pJavaStu) {
        GetPointerDataToStruct(pNativeData, 0, pJavaStu);
    }

    public static void GetPointerDataToStruct(Pointer pNativeData, long offsetOfNativeData, Structure pJavaStu) {
        pJavaStu.write();
        Pointer pJavaMem = pJavaStu.getPointer();
        pJavaMem.write(0, pNativeData.getByteArray(offsetOfNativeData, pJavaStu.size()), 0, pJavaStu.size());
        pJavaStu.read();
    }

    public static void GetPointerDataToStructArr(Pointer pNativeData, Structure[] pJavaStuArr) {
        long offset = 0;
        for (Structure structure : pJavaStuArr) {
            GetPointerDataToStruct(pNativeData, offset, structure);
            offset += structure.size();
        }
    }

    public static void SetStructArrToPointerData(Structure[] pJavaStuArr, Pointer pNativeData) {
        long offset = 0;
        for (Structure structure : pJavaStuArr) {
            SetStructDataToPointer(structure, pNativeData, offset);
            offset += structure.size();
        }
    }

    public static void SetStructDataToPointer(Structure pJavaStu, Pointer pNativeData, long offsetOfNativeData) {
        pJavaStu.write();
        Pointer pJavaMem = pJavaStu.getPointer();
        pNativeData.write(offsetOfNativeData, pJavaMem.getByteArray(0, pJavaStu.size()), 0, pJavaStu.size());
    }

    public static void savePicture(byte[] buffer, String destination) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(destination)) {
            outputStream.write(buffer);
        }
    }

    public static void savePicture(byte[] buffer, int offset, int size, String destination) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(destination)) {
            outputStream.write(buffer, offset, size);
        }
    }

    public static void savePicture(Pointer buffer, int size, String destination) throws IOException {
        savePicture(buffer.getByteArray(0, size), destination);
    }

    public static void savePicture(Pointer buffer, int offset, int size, String destination) throws IOException {
        savePicture(buffer.getByteArray(offset, size), destination);
    }

    public static String getErrorCodePrint() {
        return String.format("LastError[0x%x]", NetSDKLib.NETSDK_INSTANCE.CLIENT_GetLastError());
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }
}
