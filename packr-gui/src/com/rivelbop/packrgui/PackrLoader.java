package com.rivelbop.packrgui;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;

public class PackrLoader {

    public static void main(String[] args) {
        PackrConfig config = new PackrConfig();
        config.platform = PackrConfig.Platform.MacOS;
        config.jdk = "jdk8u402-b06"; //
        //config.jrePath = "jdk8u402-b06/Contents/Home/jre";
        config.executable = "jApp"; //
        config.classpath = Arrays.asList("desktop.jar"); //
        config.removePlatformLibs = config.classpath; //
        config.mainClass = "com.rivelbop.packrgui.Main"; //
        config.vmArgs = Arrays.asList("Xmx1G", "-XstartOnFirstThread"); //
        config.minimizeJre = "hard";
        config.outDir = new java.io.File("out-mac");
        config.useZgcIfSupportedOs = true;

        try {
			new Packr().pack(config);
		} catch (IOException | CompressorException | ArchiveException e) {
			e.printStackTrace();
		}
    }
    
}