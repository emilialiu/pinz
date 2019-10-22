package com.dimine.bogen.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;

/**
 * <p>
 * 文件处理
 * </p>
 * 
 * @author Aaron 2014-12-12
 * 
 */
public class FileUtil {

	/**
	   * 读取源文件内容
	   * @param filename String 文件路径
	   * @throws IOException
	   * @return byte[] 文件内容
	   */
	  public static byte[] readFile(String filename) throws IOException {

		File file = new File(filename);
		if (filename == null || filename.equals("")) {
			throw new NullPointerException("无效的文件路径");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();

		return bytes;

	  }

	/**
	 * writeFile
	 * 
	 * @param data
	 *            byte[]
	 * @throws IOException
	 */
	public static void writeFile(byte[] data, String filename)
			throws IOException {
		File file = new File(filename);
		file.getParentFile().mkdirs();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				new FileOutputStream(file));
		bufferedOutputStream.write(data);
		bufferedOutputStream.close();

	}

	private static void directoryZip(ZipOutputStream out, File f, String base)
			throws Exception {
		// 如果传入的是目录
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			// 创建压缩的子目录
			out.putNextEntry(new ZipEntry(base + "/"));
			if (base.length() == 0) {
				base = "";
			} else {
				base = base + "/";
			}
			for (int i = 0; i < fl.length; i++) {
				directoryZip(out, fl[i], base + fl[i].getName());
			}
		} else {
			// 把压缩文件加入rar中
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			byte[] bb = new byte[2048];
			int aa = 0;
			while ((aa = in.read(bb)) != -1) {
				out.write(bb, 0, aa);
			}
			in.close();
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param zos
	 * @param file
	 * @throws Exception
	 */
	private static void fileZip(ZipOutputStream zos, File file)
			throws Exception {
		if (file.isFile()) {
			zos.putNextEntry(new ZipEntry(file.getName()));
			FileInputStream fis = new FileInputStream(file);
			byte[] bb = new byte[2048];
			int aa = 0;
			while ((aa = fis.read(bb)) != -1) {
				zos.write(bb, 0, aa);
			}
			fis.close();
		} else {
			directoryZip(zos, file, "");
		}
	}

	/**
	 * 解压缩文件
	 * 
	 * @param zis
	 * @param file
	 * @throws Exception
	 */
	private static void fileUnZip(ZipInputStream zis, File file)
			throws Exception {
		ZipEntry zip = zis.getNextEntry();
		if (zip == null)
			return;
		String name = zip.getName();
		File f = new File(file.getAbsolutePath() + "/" + name);
		if (zip.isDirectory()) {
			f.mkdirs();
			fileUnZip(zis, file);
		} else {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			byte b[] = new byte[2048];
			int aa = 0;
			while ((aa = zis.read(b)) != -1) {
				fos.write(b, 0, aa);
			}
			fos.close();
			fileUnZip(zis, file);
		}
	}

	/**
	 * 对directory目录下的文件压缩，保存为指定的文件zipFile
	 * 
	 * @param directory
	 * @param zipFile
	 */
	public static void zip(String directory, String zipFile) {
		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					zipFile));
			fileZip(zos, new File(directory));
			zos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压缩文件zipFile保存在directory目录下
	 * 
	 * @param directory
	 * @param zipFile
	 */
	public static void unZip(String directory, String zipFile) {
		try {
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(zipFile));
			File f = new File(directory);
			f.mkdirs();
			fileUnZip(zis, f);
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key的路径文件获得持久化成文件的key
	 * 
	 * @param keyPath
	 * @return
	 */
	public static Key getKey(String keyPath) throws Exception {
		Key key = null;
		FileInputStream fis = new FileInputStream(keyPath);
		ObjectInputStream ofs = new ObjectInputStream(fis);
		key = (Key) ofs.readObject();
		return key;
	}

	/**
	 * 把文件srcFile加密后存储为destFile
	 * 
	 * @param srcFile
	 * @param destFile
	 */
	private static void encrypt(String srcFile, String destFile, Key privateKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile);
		byte[] b = new byte[53];
		while (fis.read(b) != -1) {
			fos.write(cipher.doFinal(b));
		}
		fos.close();
		fis.close();
	}

	/**
	 * 把文件srcFile解密后存储为destFile
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param privateKey
	 * @throws Exception
	 */
	private static void decrypt(String srcFile, String destFile, Key privateKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile);
		byte[] b = new byte[64];
		while (fis.read(b) != -1) {
			fos.write(cipher.doFinal(b));
		}
		fos.close();
		fis.close();
	}

	/**
	 * 对目录srcFile下的所有文件目录进行先压缩后操作,然后保存为destfile
	 * 
	 * @param srcFile
	 *            要操作的目录
	 * @param destfile
	 *            压缩加密后存放的文件名
	 * @param keyfile
	 *            公钥存放地点
	 */
	public static void encryptZip(String srcFile, String destfile,
			String keypath) throws Exception {
		File temp = new File(UUID.randomUUID().toString() + ".zip");
		temp.deleteOnExit();
		// 先压缩文件
		zip(srcFile, temp.getAbsolutePath());
		// 对文件加密
		encrypt(temp.getAbsolutePath(), destfile, FileUtil.getKey(keypath));
		temp.delete();
	}

	/**
	 * 对文件srcfile进行先解密后解压缩,然后解压缩到目录destfile下
	 * 
	 * @param srcfile
	 *            要解密和解压缩的文件名
	 * @param destfile
	 *            解压缩后的目录
	 * @param publicKey
	 *            公钥
	 */
	public static void decryptUnzip(String srcfile, String destfile,
			String keypath) throws Exception {
		// 先对文件解密
		File temp = new File(UUID.randomUUID().toString() + ".zip");
		temp.deleteOnExit();
		decrypt(srcfile, temp.getAbsolutePath(), FileUtil.getKey(keypath));
		// 解压缩
		unZip(destfile, temp.getAbsolutePath());
		temp.delete();
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/file
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件或文件夹
	 */
	public static boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory())
						delfile.delete();
					else if (delfile.isDirectory())
						deletefile(delpath + "\\" + filelist[i]);
				}
				file.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFileName
	 * @param dtnFileName
	 */
	public static void moveFile(String srcFileName, String dtnFileName) {
		File srcFile = new File(srcFileName);// 存在的源文件
		File dtnFile = new File(dtnFileName); // 制定目标文件路径以及文件名
		srcFile.renameTo(dtnFile);
	}

	/**
	 * 是否有子文件
	 */
	public static boolean hasSubFile(String filepath) {
		if (filepath == null || "".equals(filepath)) {
			return false;
		}
		File[] tmp = new File(filepath).listFiles();
		if (tmp != null && tmp.length > 0)
			return true;
		return false;
	}

	public static void main(String args[]) throws Exception {
		// 解密
//		FileUtil.decryptUnzip("D:/zb/savedata/1/xz/200904231927.zip", "D:/zb/savedata/1/xz", "D:/zb/savedata/public.key");
		FileUtil.unZip("D:/zb/savedata/1/xz/200904231927","D:/zb/savedata/1/xz/200904231927.zip");
	}
}
