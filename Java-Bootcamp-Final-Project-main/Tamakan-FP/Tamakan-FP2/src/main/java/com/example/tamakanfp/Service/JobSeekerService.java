package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.JobSeekerDTO;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import com.example.tamakanfp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSeekerService {


    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;


    public void regester(JobSeekerDTO jobSeekerDTO) {
        User user = new User(jobSeekerDTO.getUser_id(),jobSeekerDTO.getUsername(),jobSeekerDTO.getPassword(),jobSeekerDTO.getEmail(),jobSeekerDTO.getRole(),null,null);
        JobSeeker jobSeeker = new JobSeeker(null, jobSeekerDTO.getName(),jobSeekerDTO.getPhoneNumber(),jobSeekerDTO.getGender(),jobSeekerDTO.getCity(),jobSeekerDTO.getAddress(),jobSeekerDTO.getAge(),user,null,null);
        jobSeekerRepository.save(jobSeeker);
    }

    public  void  updateJobSeeker (Integer user_id , Integer id , JobSeekerDTO jobSeekerDTO){
        User user = userRepository.findUserById(user_id);
        JobSeeker oldJobSeeker= jobSeekerRepository.findJopSeekerById(id);
        if (user==null){
            throw new ApiException("user not found");
        }
        if (oldJobSeeker == null) {
            throw new ApiException("job Provider is null");
        }

        if (oldJobSeeker.getUser().getId() != user_id){
            throw new ApiException("user is not allowed");
        }

        user.setUsername(jobSeekerDTO.getUsername());
        user.setPassword(jobSeekerDTO.getPassword());
        user.setEmail(jobSeekerDTO.getEmail());
        user.setRole(jobSeekerDTO.getRole());
        oldJobSeeker.setUser(user);
        oldJobSeeker.setName(jobSeekerDTO.getName());
        oldJobSeeker.setPhoneNumber(jobSeekerDTO.getPhoneNumber());
        oldJobSeeker.setGender(jobSeekerDTO.getGender());
        oldJobSeeker.setCity(jobSeekerDTO.getCity());
        oldJobSeeker.setAddress(jobSeekerDTO.getAddress());
        oldJobSeeker.setAge(jobSeekerDTO.getAge());

        jobSeekerRepository.save(oldJobSeeker);

    }






//    private final CustomerRepository customerRepository;
//    private final UserRepository userRepository;
//
//    public void regester(CustomerDTO customerDTO)
//    {
//
//        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
//
//
//        User user1 = new User(null,customerDTO.getUsername(), hash, "Customer",null,null);
//        Customer customer =new Customer(null,user1,null,null);
//        user1.setCustomer(customer);
//
//        customerRepository.save(customer);
//        userRepository.save(user1);
//    }





}
