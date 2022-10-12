package com.example.demoSpringSellerJwt.Controller;

import com.example.demoSpringSellerJwt.Configuration.JwtRequestFilter;
import com.example.demoSpringSellerJwt.Entity.ProductEntity;
import com.example.demoSpringSellerJwt.Entity.ResponseEntity.Response;
import com.example.demoSpringSellerJwt.Entity.SellerEntity;
import com.example.demoSpringSellerJwt.Entity.SellerStatus;
import com.example.demoSpringSellerJwt.Exception.UserException;
import com.example.demoSpringSellerJwt.Repository.ProductRepository;
import com.example.demoSpringSellerJwt.Service.SellerService;
import com.example.demoSpringSellerJwt.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Profile(value = {"local","dev","prod"}) //these line is not needed for profiles environment setUp (optional)
@RestController
public class SellerController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtUtil jwtUtil;


    @PostConstruct
    public void initSellerRoleAdd(){
        sellerService.initSellerRoleAdd();
    }

    @PostMapping({"/registerNewAdmin"})
    public Response registerNewAdmin(@RequestBody SellerEntity seller){
        Response response=new Response();
        try{
            if(seller.getSellerFirstName().isEmpty() || seller.getSellerFirstName()==null){
                throw new UserException("Admin Seller_First_name should not be Empty or Null.");
            }
            if(seller.getSellerLastName().isEmpty() || seller.getSellerLastName()==null){
                throw new UserException("Admin Seller_Last_name should not be Empty or Null.");
            }
            if(seller.getSellerEmail().isEmpty()){
                throw new UserException("Admin Seller Email_Id should not be Empty");
            }
            if(seller.getSellerPassword().isEmpty()){
                throw new UserException("Admin Seller Password should not be Empty");
            }
            sellerService.registerNewAdmin(seller);
            response.setStatus(HttpStatus.OK);
            response.setMessage("All Admin Seller Records are stored inside Data Base.");
            response.setObject(seller);
            return response;
        }
        catch (UserException e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.toString());
            response.setObject(seller);
            return response;
        }
    }


    // very important logic for admins
    @PostMapping({"/checkPoint/registerNewAdmin"})
    public Response registerNewAdmin1(@RequestBody SellerEntity seller){
        Response response=new Response();
        if(StringUtils.hasText(seller.getSellerEmail())) { // **this line is for checking each field of seller Entity
            SellerEntity s = sellerService.findByEmail(seller.getSellerEmail());
            if (s == null) {
                if(seller.getSellerFirstName().isEmpty() || seller.getSellerFirstName()==null){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("Admin SellerFirstName should not be Empty or Null.");
                    return response;
                }
                else if(seller.getSellerLastName().isEmpty() || seller.getSellerLastName()==null){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("Admin Seller_Last_name should not be Empty or Null.");
                    return response;
                }
                else if(seller.getSellerPassword().isEmpty()){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("Admin Seller Password should not be Empty");
                    return response;
                }
                sellerService.registerNewAdmin(seller);
                response.setStatus(HttpStatus.OK);
                response.setMessage("All Admin Seller's Records are stored inside Data Base.");
                response.setObject(seller);
                return response;
            }
            else {
                response.setStatus(HttpStatus.ALREADY_REPORTED);
                response.setMessage("Email already exist." + seller.getSellerEmail());
                response.setObject(seller.getSellerEmail());
                return response;
            }
        }
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Admin Seller's Email should not be empty.");
        return response;
    }


    @PostMapping({"/registerNewUser"})
    public Response registerNewUser2(@RequestBody SellerEntity seller){
        Response response=new Response();
        try{
            if(seller.getSellerFirstName().isEmpty() || seller.getSellerFirstName()==null){
                throw new UserException("Seller_First_name should not be Empty or Null.");
            }
            else if(seller.getSellerLastName().isEmpty() || seller.getSellerLastName()==null){
                throw new UserException("Seller_Last_name should not be Empty or Null.");
            }
            else if(seller.getSellerEmail().isEmpty()){
                throw new UserException("Seller Email_Id should not be Empty");
            }
            else if(seller.getSellerPassword().isEmpty()){
                throw new UserException("Seller Password should not be Empty");
            }
            else{
                sellerService.registerNewUser(seller);
                response.setStatus(HttpStatus.OK);
                response.setMessage("All Seller's Records are stored inside Data Base.");
                response.setObject(seller);
                return response;
            }
        }
        catch (UserException e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.toString());
            response.setObject(seller);
            return response;
        }
    }


    // very important logic for users
    @PostMapping({"/checkPoint/registerNewUser"})
    public Response registerNewUser1(@RequestBody SellerEntity seller){
        Response response=new Response();
        if(StringUtils.hasText(seller.getSellerEmail())) { // **this line is for checking each field of seller Entity
            SellerEntity s = sellerService.findByEmail(seller.getSellerEmail());
            if (s == null) {
                if(seller.getSellerFirstName().isEmpty() || seller.getSellerFirstName()==null){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("SellerFirstName should not be Empty or Null.");
                    return response;
                }
                else if(seller.getSellerLastName().isEmpty() || seller.getSellerLastName()==null){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("Seller_Last_name should not be Empty or Null.");
                    return response;
                }
                else if(seller.getSellerPassword().isEmpty()){
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage("Seller Password should not be Empty");
                    return response;
                }
                sellerService.registerNewUser(seller);
                response.setStatus(HttpStatus.OK);
                response.setMessage("All Seller's Records are stored inside Data Base.");
                response.setObject(seller);
                return response;
            }
            else {
                response.setStatus(HttpStatus.ALREADY_REPORTED);
                response.setMessage("Email already exist." + seller.getSellerEmail());
                response.setObject(seller.getSellerEmail());
                return response;
           }
        }
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Email should not be empty.");
        return response;
    }




    @GetMapping({"/showAllData"}) //for all data shows by Admin only
    @PreAuthorize("hasRole('Admin')")
    public Response showAllData() {
        Response response =new Response();
        Iterable<SellerEntity> sellers= sellerService.showAllData();
        try{
            if(sellers==null){
                throw new UserException("No data are found. Data Base is Empty.");
            }
            response.setStatus(HttpStatus.FOUND);
            response.setMessage("Here All Data are found.");
            response.setObject(sellers);
            return response;
        }
        catch (UserException e){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage(e.toString());
            response.setObject(sellers);
            return response;
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/showSpecificData/{sellerFirstName}") //for specific data shows by Admin only
    public Response showSpecificData(@PathVariable("sellerFirstName") String sellerFirstName) {
        //Optional<T> is used,if database has only unique record

        List<SellerEntity> seller = sellerService.showSpecificData(sellerFirstName);
        Response response=new Response();
        try{
            if(seller.isEmpty()){
                throw new UserException(sellerFirstName +" is not found inside Data Base.");
            }
            response.setStatus(HttpStatus.FOUND);
            response.setMessage(sellerFirstName +" record is found inside Data Base");
            response.setObject(seller);
            return response;
        }
        catch (UserException e){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage(e.toString());
            response.setObject(seller);
            return response;
        }
    }


    @PostMapping("/productStore")
    public Response productStore(HttpServletRequest request, @RequestBody List<ProductEntity> product) {
        Response response = new Response();
        SellerEntity seller = new SellerEntity();

        //these codes for jwt token validation checking
        String jwt = jwtRequestFilter.parseJwt(request);
        String userEmail = null;
        if (jwt == null) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("No Token Provided.");
            response.setObject(null);
            return response;
        }
        if (jwt!=null) {
            userEmail = jwtUtil.getSellerEmailFromToken(jwt);
            seller = sellerService.findByEmail(userEmail);

            //here checks the both email and status of seller
            if (seller.getSellerEmail().equals(userEmail) && seller.getStatus().equals(SellerStatus.Active)) {
                seller.setProductEntityList(product); //here all products are stored by the seller object
                sellerService.saveData(seller);

                for(ProductEntity product1 : product) {
                    int netPrice=calProductNetPrice(product1.getProductPrice(),product1.getProductDiscount());
                    product1.setProductNetPrice(netPrice);
                    productRepository.save(product1);
                }

                response.setStatus(HttpStatus.OK);
                response.setMessage("All Products are stored inside Data Base.");
                response.setObject(seller);
                return response;
            }
        }
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Product does not store because of either wrong EmailId authentication or sellerStatus is DeActivate of this particular Seller.");
        response.setObject(product);
        return response;
    }


    private int calProductNetPrice(int productPrice, int productDiscount){
        int net=productPrice * productDiscount / 100;
        int netPrice = productPrice-net;
        return netPrice;
    }


    @PutMapping("/updateStatus")
    public Response updateNormal(HttpServletRequest request, @RequestBody SellerEntity sellerEntity) {
        Response response = new Response();
        SellerEntity seller = new SellerEntity();

        //these codes for jwt token validation checking
        String jwt = jwtRequestFilter.parseJwt(request);
        String userEmail = null;
        if (jwt == null) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("No Token Provided.");
            response.setObject(null);
            return response;
        } else if (jwt != null) {
            userEmail = jwtUtil.getSellerEmailFromToken(jwt);
            seller = sellerService.findByEmail(userEmail);
            if (seller.getSellerEmail().equals(sellerEntity.getSellerEmail())) {
                sellerService.updateNormal(sellerEntity);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Seller Status is changed.");
                response.setObject(sellerEntity);
                return response;
            }
        }
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Seller Status can't change due to wrong user Authentication.");
        response.setObject(seller);
        return response;
    }






    /* this method is same as below only different is return type */

//    @GetMapping("/paginationSortQuery")
//    public Response findAllByPaginationUsingQuery(@RequestParam(value = "limit") int limit, //page size
//                                                  @RequestParam(value = "page") int page, //page number
//                                                  @RequestParam(value = "order_by_column", required = false, defaultValue = "sellerId") String orderByColumn,
//                                                  @RequestParam(value = "sort_desc", required = false, defaultValue = "false") boolean sortDesc,
//                                                  @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey){
//
//        Map<String, String> map = new HashMap<String, String>();
//        String s = map.getOrDefault(orderByColumn, orderByColumn); //for sort data asc or desc
//
//        Sort sort = Sort.by(s);
//        if (sortDesc) {
//            sort = sort.descending();
//        } else {
//            sort = sort.ascending();
//        }
//
//        page = page <= 0 ? 1 : page;
//        limit = limit == 0 ? 5 : limit;
//
//        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
//        List<SellerEntity> sellerList=sellerService.paginationSort(searchKey,pageRequest);
//        Response response=new Response();
//        try{
//            if(sellerList.isEmpty())
//            {
//                throw new NoSuchFieldException("No such sorted and pageable data are exist in this page.");
//            }
//            else{
//                response.setStatus(HttpStatus.OK);
//                response.setMessage("sorted pageable Seller list is found.");
//                response.setObject(sellerList);
//                return response;
//            }
//        }
//        catch (NoSuchFieldException e)
//        {
//            response.setStatus(HttpStatus.NOT_FOUND);
//            response.setMessage(e.toString());
//            response.setObject(sellerList);
//            return response;
//        }
//
//    }


    @GetMapping("/paginationSortQuery")
    public ResponseEntity<?> findAllByPaginationUsingQuery(@RequestParam(value = "pageSize") int pageSize, //page size
                                                        @RequestParam(value = "pageNumber") int pageNumber, //page number
                                                        @RequestParam(value = "order_by_column", required = false, defaultValue = "sellerId") String orderByColumn,
                                                        @RequestParam(value = "sort_desc", required = false, defaultValue = "false") boolean sortDesc,
                                                        @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey){

        Map<String, String> map = new HashMap<String, String>();
        String s = map.getOrDefault(orderByColumn, orderByColumn); //for sort data asc or desc

        Sort sort = Sort.by(s);
        if (sortDesc) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        pageSize = pageSize == 0 ? 5 : pageSize;

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<SellerEntity> sellerList=sellerService.paginationSort(searchKey,pageRequest);
        Response response=new Response();
        try{
            if(sellerList.isEmpty())
            {
                throw new NoSuchFieldException("No such sorted and pageable data are exist in this page.");
            }
            else{

                return ResponseEntity.ok(new Response(HttpStatus.OK,"sorted pageable Seller list is found.",sellerList));
            }
        }
        catch (NoSuchFieldException e)
        {
            return ResponseEntity.ok(new Response(HttpStatus.NOT_FOUND,e.toString(),sellerList));
        }
    }


    /* setUp Corn Scheduler */
    public void schedule(){
        sellerService.displayAllSeller();
    }

}
