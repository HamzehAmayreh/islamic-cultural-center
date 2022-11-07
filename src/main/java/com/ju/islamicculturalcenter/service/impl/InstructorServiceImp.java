//package com.ju.islamicculturalcenter.service.impl;
//
//import com.ju.islamicculturalcenter.dto.response.instructor.InstructorRegistrationResponseDto;
//import com.ju.islamicculturalcenter.entity.InstructorEntity;
//import com.ju.islamicculturalcenter.repos.InstructorRepo;
//import com.ju.islamicculturalcenter.service.iservice.InstructorService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.util.Objects.nonNull;
//
//public class InstructorServiceImp implements InstructorService {
//    private final InstructorRepo instructorRepo;
//
//    public InstructorServiceImp(InstructorRepo irepo) {
//        instructorRepo = irepo;
//    }
//
//    @Override
//    public List<InstructorRegistrationResponseDto> findAll() {
//        return instructorRepo.findAllByIsActive(true).stream().map(instructor -> mapInstructorToDto(instructor)).collect(Collectors.toList());
//    }
//
//    @Override
//    public InstructorEntity findById(Long theId) {
//        InstructorEntity inst = null;
//        InstructorEntity res = instructorRepo.findByIdAndIsActive(theId, true);
//        if (nonNull(res)) {
//            inst = res;
//        } else {
//            throw new RuntimeException("Did not find Instructor id -" + theId);
//
//        }
//        return inst;
//
//    }
//
//    @Override
//    public void save(com.ju.islamicculturalcenter.dto.request.instructor.InstructorRegistrationRequestDto regReq) {
//        instructorRepo.save(mapDtoToInstructor(regReq));
//    }
//
//    @Override
//    public void deleteById(Long theId) {
//        instructorRepo.deleteById(theId);
//
//    }
//}
