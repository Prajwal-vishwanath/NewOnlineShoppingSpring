package com.lti.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CategoryDto;
import com.lti.dto.ProductDto;
import com.lti.dto.ProductPictureDto;
import com.lti.dto.UserDto;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.service.CartService;
import com.lti.service.ProductService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class ProductResource {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@GetMapping(value = "/displayProductDetails/{productId}")
	public Product displayProductDetailsById(@PathVariable("productId") long productId) {
		return productService.findProductById(productId);
	}

	@PostMapping(value = "/addProduct")
	public Product addProduct(@RequestBody Product product) {
		Product prod = productService.addOrUpdateProduct(product);
		return prod;

	}

	@GetMapping(value = "/viewAllProducts")
	public List<Product> viewAllProducts() {
		List<Product> prods = productService.viewAllProducts();
		return prods;
	}

	@GetMapping(value = "/viewAllApprovedProducts")
	public List<Product> viewAllApprovedProducts() {
		List<Product> prods = productService.viewAllApprovedProducts();
		return prods;
	}

	@PostMapping(value = "/uploadProductPic")
	public Product uploadProduct(ProductPictureDto productPicture) {
		long productId = productPicture.getProductId();
		String imgUploadLocation = "D:/uploads/";
		String uploadedFileName = productPicture.getProductImg().getOriginalFilename();
		String newFileName = productId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(productPicture.getProductImg().getInputStream(), new FileOutputStream(targetFileName));
		} catch (IOException e) {
			e.printStackTrace(); // hoping no error would occur
		}
		Product product = productService.findProductById(productId);
		product.setProductImg(newFileName);
		productService.addOrUpdateProduct(product);
		return product;

	}

	@GetMapping("/downloadproduct")
	public Product viewProductById(@RequestParam("productId") int productId, HttpServletRequest request) {
		Product product = productService.findProductById(productId);
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();
		String targetFile = tempDownloadPath + product.getProductImg();
		System.out.println(tempDownloadPath);
		String uploadedImagesPath = "D:/uploads/";
		String sourceFile = uploadedImagesPath + product.getProductImg();
		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("not done");
		}
		return product;
	}

//------------------------------------------
	@GetMapping("/downloadproducts")
	public List<Product> viewAllProducts(HttpServletRequest request) {
		List<Product> products = productService.viewAllApprovedProducts();
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();
		String targetFile;
		String sourceFile;
		String uploadedImagesPath = "D:/uploads/";
		for (Product product : products) {
			targetFile = tempDownloadPath + product.getProductImg();
			System.out.println(tempDownloadPath);
			// uploadedImagesPath = "D:/uploads/";
			sourceFile = uploadedImagesPath + product.getProductImg();
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
		}
		return products;
	}

	@GetMapping(value = "/viewAllProductsToBeApproved")
	public List<Product> viewAllProductsToBeApproved() {
		List<Product> prods = productService.viewAllProductsToBeApproved();
		return prods;
	}

	@GetMapping(value = "/listAllCategories")
	public CategoryDto listAllCategories() {
		 return  productService.listAllCategories();
			
	}
	
	@GetMapping(value = "/viewProductsByCategory")
	public List<Product> viewProductsByCategory(@RequestParam("category") String category) {
		return productService.viewAllProductByCategoryName(category);
	}
	
	@GetMapping(value = "/filterByProductName")
	public List<Product> filterByProductName(@RequestParam("productName") String productName){
		return productService.filterByProductName(productName);
	}
	
}
