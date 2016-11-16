package com.massclouds.ns.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.tempuri1.FRSServiceStub;
import org.tempuri1.FRSServiceStub.AddCamerasOfUserResponse;
import org.tempuri1.FRSServiceStub.ArrayOfFaceLibraryInfo;
import org.tempuri1.FRSServiceStub.ArrayOfHitRecordExtend;
import org.tempuri1.FRSServiceStub.ArrayOfMacHistoryInfo;
import org.tempuri1.FRSServiceStub.ArrayOfNotHitRecordExtend;
import org.tempuri1.FRSServiceStub.ArrayOfSimilarFace;
import org.tempuri1.FRSServiceStub.ArrayOfUserIdArray;
import org.tempuri1.FRSServiceStub.CancelShareToUserResponse;
import org.tempuri1.FRSServiceStub.CheckMacAppearedResponse;
import org.tempuri1.FRSServiceStub.DeEnrollResponse;
import org.tempuri1.FRSServiceStub.DeleteCamerasOfUserResponse;
import org.tempuri1.FRSServiceStub.DeleteFaceLibrary;
import org.tempuri1.FRSServiceStub.DeleteUserIncCamsLibsResponse;
import org.tempuri1.FRSServiceStub.FaceLibraryInfo;
import org.tempuri1.FRSServiceStub.FaceVerifyResponse;
import org.tempuri1.FRSServiceStub.GetHitRecordByIDResponse;
import org.tempuri1.FRSServiceStub.GetHitRecordTotalResponse;
import org.tempuri1.FRSServiceStub.GetMacHistoryInfoResponse;
import org.tempuri1.FRSServiceStub.GetNotHitRecordTotalResponse;
import org.tempuri1.FRSServiceStub.GetServiceStatusResponse;
import org.tempuri1.FRSServiceStub.GetTotalAlertOfCameraResponse;
import org.tempuri1.FRSServiceStub.GetTotalCaptureOfUserResponse;
import org.tempuri1.FRSServiceStub.GetTotalOfLibraryResponse;
import org.tempuri1.FRSServiceStub.HitRecordExtend;
import org.tempuri1.FRSServiceStub.MacHistoryInfo;
import org.tempuri1.FRSServiceStub.NotHitRecordExtend;
import org.tempuri1.FRSServiceStub.SearchFaceImageResponse;
import org.tempuri1.FRSServiceStub.ShareLibrariesToUserResponse;
import org.tempuri1.FRSServiceStub.SimilarFace;
import org.tempuri1.FRSServiceStub.TestVelocityResponse;
import org.tempuri1.FRSServiceStub.UserBaseExtend;
import org.tempuri1.FRSServiceStub.UserIdArray;

import com.massclouds.ns.test.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * webservice服务类
 * <p>
 * 
 * 创建日期 2016年5月1日<br>
 * 
 * @author xielipeng<br>
 */
public class WebServiceServe {
	private static WebServiceServe obj = null;
	private String user = "super_admin_1001";
	private String password = "sa@123";
	private String key;
	private FRSServiceStub frss;

	// 私有构造方法
	private WebServiceServe() {
		try {
			frss = new FRSServiceStub();
			FRSServiceStub.Login lg = new FRSServiceStub.Login();
			lg.setName(this.user); // 设置参数1
			lg.setPassword(this.password);
			// 设置参数2
			FRSServiceStub.LoginResponse lgr = frss.login(lg);
			key = lgr.getLoginResult();
			System.out.println(key);
		} catch (Exception e) {
			e.printStackTrace();
			obj = null;
		}
	}

	// 取对象
	public static WebServiceServe getObj() {
		if (obj == null) {
			obj = new WebServiceServe();
		}
		return obj;
	}
	
	
	// 获取实时抓拍信息(筛选)
		public NotHitRecordExtend[] GetLatestCapturedFacesEx(String userType,
				int timespan,String sourceId,int topN) {
			try {
				FRSServiceStub.GetLatestCapturedFacesEx glcfe = new FRSServiceStub.GetLatestCapturedFacesEx();
				glcfe.setUserType(userType); // 设置参数1
				glcfe.setTimespan(timespan); // 设置参数2
				glcfe.setSourceId(sourceId);
				glcfe.setTopN(topN);
				glcfe.setKey(this.key);
				FRSServiceStub.GetLatestCapturedFacesExResponse glcfer = frss
						.getLatestCapturedFacesEx(glcfe);
				ArrayOfNotHitRecordExtend aglcfer = glcfer
						.getGetLatestCapturedFacesExResult();

				NotHitRecordExtend[] array = aglcfer.getNotHitRecordExtend();
				
				// 图片保存本地（暂不使用）
				// for (int i = 0; i < array.length; i++) { // 图片解码
				// String picname = "d://WWW/face/image/nothit/"
				// + array[i].getId() + ".jpg"; // 保存图片名称
				// String picpath = "http://localhost/face/image/nothit/"
				// + array[i].getId() + ".jpg";// 显示图片名称
				//
				// if (GenerateImage(array[i].getFaceStr(), picname)) {
				// array[i].setFaceStr(picpath);
				// } else {
				// throw new Exception("图片解码失败");
				// }
				// }

				return array;

			} catch (Exception e) {
				e.printStackTrace();
				obj = null; // 重新至空
				return null;
			}

		}
	

	// 获取实时抓拍信息
	public NotHitRecordExtend[] GetLatestCapturedFaces(String userType,
			int timespan) {
		try {
			FRSServiceStub.GetLatestCapturedFaces glcf = new FRSServiceStub.GetLatestCapturedFaces();
			glcf.setUserType(userType); // 设置参数1
			glcf.setTimespan(timespan); // 设置参数2
			glcf.setKey(this.key);
			FRSServiceStub.GetLatestCapturedFacesResponse glcfr = frss
					.getLatestCapturedFaces(glcf);
			ArrayOfNotHitRecordExtend aglcfr = glcfr
					.getGetLatestCapturedFacesResult();

			NotHitRecordExtend[] array = aglcfr.getNotHitRecordExtend();
			// 图片保存本地（暂不使用）
			// for (int i = 0; i < array.length; i++) { // 图片解码
			// String picname = "d://WWW/face/image/nothit/"
			// + array[i].getId() + ".jpg"; // 保存图片名称
			// String picpath = "http://localhost/face/image/nothit/"
			// + array[i].getId() + ".jpg";// 显示图片名称
			//
			// if (GenerateImage(array[i].getFaceStr(), picname)) {
			// array[i].setFaceStr(picpath);
			// } else {
			// throw new Exception("图片解码失败");
			// }
			// }

			return array;

		} catch (Exception e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}

	}

	// 获取实时报警信息
	public HitRecordExtend[] GetLatestHitRecord(String userType, int timespan) {
		try {
			frss = new FRSServiceStub();
			FRSServiceStub.GetLatestHitRecord glhr = new FRSServiceStub.GetLatestHitRecord();
			glhr.setUserType(userType); // 设置参数1
			glhr.setTimespan(timespan); // 设置参数2
			glhr.setKey(this.key);

			FRSServiceStub.GetLatestHitRecordResponse glhrr = frss
					.getLatestHitRecord(glhr);
			ArrayOfHitRecordExtend aohre = glhrr.getGetLatestHitRecordResult();

			HitRecordExtend[] array = aohre.getHitRecordExtend();

			if (array == null || array.length == 0) {
				return null;
			}

			// 图片保存本地（暂时弃用）
			// for (int i = 0; i < array.length; i++) { // 图片解码
			// String picname = "d://WWW/face/image/hit/" + array[i].getId()
			// + ".jpg"; // 保存图片名称
			// String picpath = "http://localhost/face/image/hit/"
			// + array[i].getId() + ".jpg";// 显示图片名称
			//
			// if (GenerateImage(array[i].getQueryImage(), picname)) {
			// array[i].setQueryImage(picpath);
			// } else {
			// throw new Exception("图片解码失败");
			// }
			// }

			return array;
		} catch (Exception e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}
	

	}

	// 添加人像库
	public int addFaceLibrary(String name, String user) {
		try {
			FRSServiceStub.AddFaceLibrary afl = new FRSServiceStub.AddFaceLibrary();
			afl.setName(name);
			afl.setUser(user);
			afl.setKey(this.key);
			FRSServiceStub.AddFaceLibraryResponse aflr = frss
					.addFaceLibrary(afl);
			int statu = aflr.getAddFaceLibraryResult();
			return statu;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}

		
	}

	// 删除人像库
	public int deleteFaceLibrary(String name, String user) {
		try {
			DeleteFaceLibrary dfl = new FRSServiceStub.DeleteFaceLibrary();
			dfl.setName(name);
			dfl.setUser(user);
			dfl.setKey(this.key);
			FRSServiceStub.DeleteFaceLibraryResponse dflr = frss
					.deleteFaceLibrary(dfl);
			int statu = dflr.getDeleteFaceLibraryResult();
			return statu;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}
	
	}

	// 查询人像库
	public FaceLibraryInfo[] GetFaceLibraries(String user) {
		try {
			FRSServiceStub.GetFaceLibraries gfl = new FRSServiceStub.GetFaceLibraries();
			gfl.setUser(user);
			gfl.setKey(this.key);
			FRSServiceStub.GetFaceLibrariesResponse gflr = frss
					.getFaceLibraries(gfl);
			ArrayOfFaceLibraryInfo arrGflr = gflr.getGetFaceLibrariesResult();

			FaceLibraryInfo[] array = arrGflr.getFaceLibraryInfo();
			if (array == null || array.length == 0) {
				return null;
			}
			return array;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}

		
	}

	// 单例注册
	public int EnrollUser(Map<String, String[]> map) {
		try {
			FRSServiceStub.EnrollUser eru = new FRSServiceStub.EnrollUser();
			eru.setName(map.get("name")[0]);
			eru.setPassport(map.get("passport")[0]);
			eru.setGender(map.get("gender")[0]);
			eru.setNation(map.get("nation")[0]);
			eru.setType(map.get("type")[0]);
			eru.setComment(map.get("comment")[0]);
			eru.setBorned(map.get("borned")[0]);
			eru.setImage(map.get("image")[0]);
			eru.setMac(map.get("mac")[0]);
			eru.setKey(this.key);
			FRSServiceStub.EnrollUserResponse erur = frss.enrollUser(eru);
			int statu = erur.getEnrollUserResult();
			return statu;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}
	}

	// 反向单例注册（删除）
	public int DeEnroll(Integer userID) {
		try {
			FRSServiceStub.DeEnroll de = new FRSServiceStub.DeEnroll();
			de.setUserID(userID);
			de.setKey(this.key);
			DeEnrollResponse der = frss.deEnroll(de);
			int statu = der.getDeEnrollResult();
			return statu;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
		}
		return -100;
	}

	// 最近注册人脸(id数组)
	public UserIdArray[] GetLatestEnrolledUsers(String lib, int n) {
		try {
			FRSServiceStub.GetLatestEnrolledUsers gleu = new FRSServiceStub.GetLatestEnrolledUsers();
			gleu.setFaceLib(lib);
			gleu.setTopN(n);
			gleu.setKey(this.key);
			FRSServiceStub.GetLatestEnrolledUsersResponse gleur = frss
					.getLatestEnrolledUsers(gleu);
			ArrayOfUserIdArray arrGleur = gleur
					.getGetLatestEnrolledUsersResult();
			UserIdArray[] array = arrGleur.getUserIdArray();
			if (array == null || array.length == 0) {
				return null;
			}
			return array;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 获取人脸信息 （按Id）
	public UserBaseExtend GetUserBase(Integer id) {
		try {
			FRSServiceStub.GetUserBase gub = new FRSServiceStub.GetUserBase();
			gub.setUserID(id);
			gub.setKey(this.key);
			FRSServiceStub.GetUserBaseResponse gubr = frss.getUserBase(gub);
			UserBaseExtend ube = gubr.getGetUserBaseResult();
			if (ube == null) {
				return null;
			}
			return ube;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 获取人脸信息 （按姓名 和 身份证号码）
	public UserBaseExtend GetUser(String name, String passportNumber) {
		try {
			FRSServiceStub.GetUser gu = new FRSServiceStub.GetUser();
			gu.setName(name);
			gu.setPassportNumber(passportNumber);
			gu.setKey(this.key);

			FRSServiceStub.GetUserResponse gur = frss.getUser(gu);
			UserBaseExtend ube = gur.getGetUserResult();
			if (ube == null) {
				return null;
			}
			return ube;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 查询符合条件的比中记录(总数目)
	public Integer GetHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId, String username) {
		try {
			FRSServiceStub.GetHitRecordTotal ghrt = new FRSServiceStub.GetHitRecordTotal();
			// 设置参数
			ghrt.setKey(this.key);
			ghrt.setUserType(userType);
			ghrt.setDateFrm(dtFrom);
			ghrt.setDateTo(dtTo);
			ghrt.setSourceId(strSourceId);
			ghrt.setUserName(username);
			ghrt.setPSize(10);

			GetHitRecordTotalResponse ghrtr = frss.getHitRecordTotal(ghrt);
			Integer gghrtr = ghrtr.getGetHitRecordTotalResult();

			return gghrtr;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return 0;
	}

	// 查询符合条件的比中记录
	public HitRecordExtend[] GetHitRecord(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId,
			String username) {
		try {
			FRSServiceStub.GetHitRecord ghr = new FRSServiceStub.GetHitRecord();
			// 设置参数
			ghr.setKey(this.key);
			ghr.setUserType(userType);
			ghr.setNPageSize(nPageSize);
			ghr.setNPage(nPage);
			ghr.setDtFrom(dtFrom);
			ghr.setDtTo(dtTo);
			ghr.setStrSourceId(strSourceId);
			ghr.setUsername(username);

			FRSServiceStub.GetHitRecordResponse ghrr = frss.getHitRecord(ghr);
			ArrayOfHitRecordExtend ahre = ghrr.getGetHitRecordResult();
			HitRecordExtend[] array = ahre.getHitRecordExtend();
			if (array == null || array.length == 0) {
				return null;
			}
			return array;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 查询符合条件的未比中记录(总数目)
	public Integer GetNotHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId) {
		try {
			FRSServiceStub.GetNotHitRecordTotal gnhrt = new FRSServiceStub.GetNotHitRecordTotal();
			// 设置参数
			gnhrt.setKey(this.key);
			gnhrt.setUserType(userType);
			gnhrt.setDateFrm(dtFrom);
			gnhrt.setDateTo(dtTo);
			gnhrt.setSourceId(strSourceId);
			gnhrt.setPSize(10);

			GetNotHitRecordTotalResponse gnhrtr = frss
					.getNotHitRecordTotal(gnhrt);
			Integer gghrtr = gnhrtr.getGetNotHitRecordTotalResult();
			return gghrtr;

		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return 0;
	}

	// 查询符合条件的未比中记录
	public NotHitRecordExtend[] GetNotHitRecord(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId) {
		try {
			FRSServiceStub.GetNotHitRecord gnhr = new FRSServiceStub.GetNotHitRecord();
			// 设置参数
			gnhr.setKey(this.key);
			gnhr.setUserType(userType);
			gnhr.setNPageSize(nPageSize);
			gnhr.setNPage(nPage);
			gnhr.setDtFrom(dtFrom);
			gnhr.setDtTo(dtTo);
			gnhr.setStrSourceId(strSourceId);

			FRSServiceStub.GetNotHitRecordResponse gnhrr = frss
					.getNotHitRecord(gnhr);
			ArrayOfNotHitRecordExtend ahre = gnhrr.getGetNotHitRecordResult();
			NotHitRecordExtend[] array = ahre.getNotHitRecordExtend();
			if (array == null || array.length == 0) {
				return null;
			}
			return array;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 条件查询相似图像
	public SimilarFace[] SearchFaceImage(String imgStr, String type,
			float threshold, int topN) {
		try {
			FRSServiceStub.SearchFaceImage sfi = new FRSServiceStub.SearchFaceImage();
			// 设置参数
			sfi.setKey(this.key);
			sfi.setImgStr(imgStr);
			sfi.setType(type);
			sfi.setThreshold(threshold);
			sfi.setTopN(topN);

			SearchFaceImageResponse sfir = frss.searchFaceImage(sfi);
			ArrayOfSimilarFace aosf = sfir.getSearchFaceImageResult();
			SimilarFace[] array = aosf.getSimilarFace();
			return array;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}

	}

	// 共享人像库给普通用户
	public Integer ShareLibrariesToUser(String currentUser, String lib,
			String user) {
		try {
			FRSServiceStub.ShareLibrariesToUser sltu = new FRSServiceStub.ShareLibrariesToUser();
			// 设置参数
			sltu.setKey(this.key);
			sltu.setCurrentUser(currentUser);
			sltu.setLib(lib);
			sltu.setUser(user);
			ShareLibrariesToUserResponse sltur = frss
					.shareLibrariesToUser(sltu);
			int result = sltur.getShareLibrariesToUserResult();

			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return -100;
	}

	// 取消共享人像库给普通用户
	public Integer CancelShareToUser(String currentUser, String lib, String user) {
		try {
			FRSServiceStub.CancelShareToUser cstu = new FRSServiceStub.CancelShareToUser();
			// 设置参数
			cstu.setKey(this.key);
			cstu.setCurrentUser(currentUser);
			cstu.setLib(lib);
			cstu.setUser(user);
			CancelShareToUserResponse cstur = frss.cancelShareToUser(cstu);
			int result = cstur.getCancelShareToUserResult();

			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return -100;
	}

	// 增加某个用户关联的摄像头
	public Integer AddCamerasOfUser(String user, String camera) {
		try {
			FRSServiceStub.AddCamerasOfUser acou = new FRSServiceStub.AddCamerasOfUser();
			// 设置参数
			acou.setKey(this.key);
			acou.setCamera(camera);
			acou.setUser(user);

			AddCamerasOfUserResponse acour = frss.addCamerasOfUser(acou);
			int result = acour.getAddCamerasOfUserResult();

			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return -100;
	}

	// 删除某个用户关联的摄像头
	public Integer DeleteCamerasOfUser(String user, String camera) {
		try {
			FRSServiceStub.DeleteCamerasOfUser dcou = new FRSServiceStub.DeleteCamerasOfUser();
			// 设置参数
			dcou.setKey(this.key);
			dcou.setCamera(camera);
			dcou.setUser(user);

			DeleteCamerasOfUserResponse dcour = frss.deleteCamerasOfUser(dcou);
			int result = dcour.getDeleteCamerasOfUserResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return -100;
	}

	// 查询指定摄像头某段时间内的报警数量
	public String GetTotalAlertOfCamera(String user, String dateFrm,
			String dateTo) {
		try {
			FRSServiceStub.GetTotalAlertOfCamera gtaoc = new FRSServiceStub.GetTotalAlertOfCamera();
			// 设置参数
			gtaoc.setKey(this.key);
			gtaoc.setUser(user);
			gtaoc.setDateFrm(dateFrm);
			gtaoc.setDateTo(dateTo);
			GetTotalAlertOfCameraResponse gtaocr = frss
					.getTotalAlertOfCamera(gtaoc);
			String result = gtaocr.getGetTotalAlertOfCameraResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;

		}

	}

	// 查询指定摄像头某段时间内的抓拍数量
	public String GetTotalCaptureOfUser(String user, String dateFrm,
			String dateTo) {
		try {
			FRSServiceStub.GetTotalCaptureOfUser gtaoc = new FRSServiceStub.GetTotalCaptureOfUser();
			// 设置参数
			gtaoc.setKey(this.key);
			gtaoc.setUser(user);
			gtaoc.setDateFrm(dateFrm);
			gtaoc.setDateTo(dateTo);

			GetTotalCaptureOfUserResponse gtaocr = frss
					.getTotalCaptureOfUser(gtaoc);
			String result = gtaocr.getGetTotalCaptureOfUserResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 查询指定用户下所有人像库的已注册数量
	public String GetTotalOfLibrary(String user) {
		try {
			FRSServiceStub.GetTotalOfLibrary gtaoc = new FRSServiceStub.GetTotalOfLibrary();
			// 设置参数
			gtaoc.setKey(this.key);
			gtaoc.setUser(user);

			GetTotalOfLibraryResponse gtaocr = frss.getTotalOfLibrary(gtaoc);
			String result = gtaocr.getGetTotalOfLibraryResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	// 获取服务状态
	public String GetServiceStatus(String user) {
		try {
			FRSServiceStub.GetServiceStatus gss = new FRSServiceStub.GetServiceStatus();
			// 设置参数
			gss.setKey(this.key);
			gss.setUser(user);
			GetServiceStatusResponse gssr = frss.getServiceStatus(gss);
			String result = gssr.getGetServiceStatusResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空

		}
		return null;
	}

	public float FaceVerify(String imgS1, String imgS2) {
		try {
			FRSServiceStub.FaceVerify fv = new FRSServiceStub.FaceVerify();
			// 设置参数
			fv.setKey(this.key);
			fv.setImgS1(imgS1);
			fv.setImgS2(imgS2);
			FaceVerifyResponse fvr = frss.faceVerify(fv);
			float result = fvr.getFaceVerifyResult();
			System.out.println(result);
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}
	}

	// 根据id查询比中记录详细信息
	public HitRecordExtend GetHitRecordByID(Integer id) {
		try {
			FRSServiceStub.GetHitRecordByID ghrbi = new FRSServiceStub.GetHitRecordByID();
			// 设置参数
			ghrbi.setKey(this.key);
			ghrbi.setId(id.toString());

			GetHitRecordByIDResponse ghrbir = frss.getHitRecordByID(ghrbi);
			HitRecordExtend result = ghrbir.getGetHitRecordByIDResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}
	}

	// 删除指定用户，清空摄像头及人像库信息
	public int DeleteUserIncCamsLibs(String user) {
		try {
			FRSServiceStub.DeleteUserIncCamsLibs du = new FRSServiceStub.DeleteUserIncCamsLibs();
			// 设置参数
			du.setKey(this.key);
			du.setUser(user);

			DeleteUserIncCamsLibsResponse dur = frss.deleteUserIncCamsLibs(du);
			int result = dur.getDeleteUserIncCamsLibsResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}
	}

	// 查询MAC地址最近是否在指定场所出现过
	public boolean CheckMacAppeared(String mac, Integer minutes, String place) {
		try {
			FRSServiceStub.CheckMacAppeared cma = new FRSServiceStub.CheckMacAppeared();
			// 设置参数
			cma.setKey(this.key);
			cma.setMac(mac);
			cma.setMinutes(minutes);
			cma.setPlace(place);

			CheckMacAppearedResponse cmar = frss.checkMacAppeared(cma);
			boolean result = cmar.getCheckMacAppearedResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return false;
		}
	}

	// 查询MAC地址最近出现的信息
	public MacHistoryInfo[] GetMacHistoryInfo(String mac, String dateFrm,
			String dateTo, String place) {
		try {
			FRSServiceStub.GetMacHistoryInfo gmh = new FRSServiceStub.GetMacHistoryInfo();
			// 设置参数
			gmh.setKey(this.key);
			gmh.setMac(mac);
			gmh.setDateFrm(dateFrm);
			gmh.setDateTo(dateTo);
			gmh.setPlace(place);

			GetMacHistoryInfoResponse gmhr = frss.getMacHistoryInfo(gmh);
			ArrayOfMacHistoryInfo result = gmhr.getGetMacHistoryInfoResult();
			return result.getMacHistoryInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return null;
		}
	}

	// test
	public int TestVelocity() {
		try {
			FRSServiceStub.TestVelocity fv = new FRSServiceStub.TestVelocity();
			// 设置参数
			fv.setKey(this.key);
			fv.setMac("123");
			TestVelocityResponse fvr = frss.testVelocity(fv);
			int result = fvr.getTestVelocityResult();
			return result;
		} catch (RemoteException e) {
			e.printStackTrace();
			obj = null; // 重新至空
			return -100;
		}
	}

	// base64字符串转化成图片
	private static boolean GenerateImage(String imgStr, String picname) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return true;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = picname;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//
	public static String GetBase64FromImage(InputStream in) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

}
