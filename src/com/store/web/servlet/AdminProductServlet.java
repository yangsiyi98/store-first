package com.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.store.domain.Category;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.Imp.CategoryServiceImp;
import com.store.service.Imp.ProductServiceImp;
import com.store.web.base.BaseServlet;
import com.store.utils.UUIDUtils;
import com.store.utils.UploadUtils;


/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	//findAllProductsWithPage
	public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//��ȡ��ǰҳ
		int curNum=Integer.parseInt(req.getParameter("num"));
		//����ҵ���ȫ����Ʒ��Ϣ����pageModel
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.finAllProductsWithPage(curNum);
		//��pageModel����request
		req.setAttribute("page", pm);
		//ת����/admin/product/list.jsp
		return "/admin/product/list.jsp";
	}
	//addProductUI
				 
	public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService CategoryService=new CategoryServiceImp();
		//��ȡȫ��������Ϣ
		List<Category> list = CategoryService.getAllCats();
		//��ȫ��������Ϣ����request
		req.setAttribute("allCats", list);
		//ת����/admin/product/add.jsp
		return "/admin/product/add.jsp";
	}
	//addProduct
		public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//�洢��������
			Map<String,String> map=new HashMap<String,String>();
			//Я�����е�������servcie,dao
			Product product=new Product();
			try {
				//����req.getInputStream();��ȡ����������ȫ������,���в�ֺͷ�װ
				DiskFileItemFactory fac=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(fac);
				List<FileItem> list=upload.parseRequest(req);
				//4_��������
				for (FileItem item : list) {
					if(item.isFormField()){
						//5_�����ǰ��FileItem��������ͨ��
						//����ͨ����name���Ե�ֵ��Ϊ��,����ȡ����������Ϊֵ,����MAP��
						// {username<==>tom,password<==>1234}
						map.put(item.getFieldName(), item.getString("utf-8"));
					}else{
						//6_�����ǰ��FileItem�������ϴ���
						
						//��ȡ��ԭʼ���ļ�����
						String oldFileName=item.getName();
						//��ȡ��Ҫ�����ļ�������   1222.doc  123421342143214.doc
						String newFileName=UploadUtils.getUUIDName(oldFileName);
						
						//ͨ��FileItem��ȡ������������,ͨ�����������Ի�ȡ��ͼƬ����������
						InputStream is=item.getInputStream();
						//��ȡ����ǰ��Ŀ��products/3�µ���ʵ·��
						//D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3
						String realPath=getServletContext().getRealPath("/products/3/");
						String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
						String path=realPath+dir; 	//�ڴ�������һ��Ŀ¼
						File newDir=new File(path);
						if(!newDir.exists()){
							newDir.mkdirs();
						}
						//�ڷ���˴���һ�����ļ�(��׺������ϴ�������˵��ļ�����׺һ��)
						File finalFile=new File(newDir,newFileName);
						if(!finalFile.exists()){
							finalFile.createNewFile();
						}
						//�����Ϳ��ļ���Ӧ�������
						OutputStream os=new FileOutputStream(finalFile);
						//���������е�����ˢ���������
						IOUtils.copy(is, os);
						//�ͷ���Դ
						IOUtils.closeQuietly(is);
						IOUtils.closeQuietly(os);
						//��map�д���һ����ֵ�Ե����� userhead<===> /image/11.bmp
						// {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
						map.put("pimage", "/products/3/"+dir+"/"+newFileName);
					}
				}

				
				//7_����BeanUtils��MAP�е�������䵽Product������
				BeanUtils.populate(product, map);
				product.setPid(UUIDUtils.getId());
				product.setPdate(new Date());
				product.setPflag(0);
				
				//8_����servcie_dao��user��Я�������ݴ������ݲֿ�,�ض��򵽲�ѯȫ����Ʒ��Ϣ·��
				ProductService ProductService=new ProductServiceImp();
				ProductService.saveProduct(product);
				
				resp.sendRedirect("/store/AdminProductServlet?method=findAllProductsWithPage&num=1");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
}
