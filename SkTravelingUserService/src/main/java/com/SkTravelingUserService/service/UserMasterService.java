package com.SkTravelingUserService.service;

import com.SkTravelingUserService.dto.ReqRes;
import com.SkTravelingUserService.entity.UsersMaster;
import com.SkTravelingUserService.repository.UsersMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserMasterService {

    @Autowired
    private UsersMasterRepo usersMasterRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            UsersMaster usersMaster = new UsersMaster();
            usersMaster.setEmail(registrationRequest.getEmail());
            usersMaster.setCity(registrationRequest.getCity());
            usersMaster.setRole(registrationRequest.getRole());
            usersMaster.setName(registrationRequest.getName());
            usersMaster.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            UsersMaster usersMasterResult = usersMasterRepo.save(usersMaster);

            if(usersMasterResult.getId() > 0){
                resp.setUsersMaster(usersMasterResult);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception ex){
            resp.setStatusCode(500);
            resp.setError(ex.getLocalizedMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword()));
            var user = usersMasterRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully logged in");
        }catch (Exception ex){
            response.setStatusCode(500);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        try{
            String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            UsersMaster usersMaster = usersMasterRepo.findByEmail(userEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), usersMaster)){
                var jwt = jwtUtils.generateToken(usersMaster);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");

            }
            response.setStatusCode(200);
            return response;
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<UsersMaster> result = usersMasterRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setUsersMasterList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }

    public ReqRes getUsersById(Integer id) {
        ReqRes reqRes = new ReqRes();
        try {
            UsersMaster usersById = usersMasterRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUsersMaster(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes deleteUser(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<UsersMaster> userOptional = usersMasterRepo.findById(userId);
            if (userOptional.isPresent()) {
                usersMasterRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, UsersMaster updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<UsersMaster> userOptional = usersMasterRepo.findById(userId);
            if (userOptional.isPresent()) {
                UsersMaster existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                UsersMaster savedUser = usersMasterRepo.save(existingUser);
                reqRes.setUsersMaster(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes getMyInfo(String email){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<UsersMaster> userOptional = usersMasterRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setUsersMaster(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}
