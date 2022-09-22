package com.example.doctor.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.doctor.controller.DoctorController;
import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
	
@RunWith(SpringRunner.class)
	@WebMvcTest(DoctorController.class)
	public class DoctorTest {
	  @MockBean
	  private DoctorRepository doctorRepository;
	  
	  @MockBean
	  private DoctorService doctorService;

	  
	  @MockBean
	  private Doctor doctor;

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Test
	  void shouldCreateDoctor() throws Exception {
	    Doctor doctor=new Doctor(5, "VL", "Female", "psycharstist", 1);

	    mockMvc.perform(post("/doctors").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(doctor)))
	        .andExpect(status().isOk())
	        .andDo(print());
	  }

	  @Test
	  void shouldReturnDoctor() throws Exception {
	    int id=5;
	    String name="VL";
	    Doctor doctor=new Doctor(id, name, "Female", "psycharstist", 1);

	    when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
	    mockMvc.perform(get("/doctors/get/{id}", id)).andExpect(status().isOk())
	        .andDo(print());
	  }
	  

	  
//	  '1', '24', 'Male', 'Ramana', '10', 'ent'
//	  '2', '23', 'Female', 'JK', '3', 'Brain'
//	  '3', '34', 'Male', 'sri', '3', 'Brain'
//	  '4', '22', 'Female', 'lakshmi', '1', 'mental disorder'
//	  '5', '55', 'Female', 'VL', '1', 'psycharstist'


	 // @Test
	  //void shouldReturnListOfDoctors() throws Exception {
	    //List<Doctor> doctors = new ArrayList<>(
	      //  Arrays.asList(new Doctor(1, "Ramana", "Male", "ent", 10,24),
	        //    new Doctor(2, "JK", "Female", "Brain", 3,23),
	          //  new Doctor(3, "sri", "Male", "Brain", 3,34),
	           // new Doctor(4, "lakshmi", "Female", "mental disorder", 1, 22),
	            //new Doctor(5, "VLC", "Female", "psycharstist", 1, 55)
	            //));

	  //  when(doctorRepository.findAll()).thenReturn(doctors);
	    //mockMvc.perform(get("/doctors/"))
	      //  .andExpect(status().isOk())
	        //.andExpect(jsonPath("$.size()").value(doctors.size()))
	        //.andDo(print());
	  //}

}