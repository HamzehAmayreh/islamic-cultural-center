package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.request.RegReqInstructorDto;
import com.ju.islamicculturalcenter.dto.response.RegResInstructorDto;
import com.ju.islamicculturalcenter.entity.Instructor;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.service.iservice.InstructorService;
import com.ju.islamicculturalcenter.service.mapper.AdminStudentMapper;

import java.util.List;
import java.util.stream.Collectors;

import static com.ju.islamicculturalcenter.service.mapper.InstructorMapper.mapDtoToInstructor;
import static com.ju.islamicculturalcenter.service.mapper.InstructorMapper.mapInstructorToDto;
import static java.util.Objects.nonNull;

public class InstructorServiceImp implements InstructorService {
    private final InstructorRepo instructorRepo;

    public InstructorServiceImp(InstructorRepo irepo){
        instructorRepo =irepo;
    }

    @Override
    public List<RegResInstructorDto> findAll() {
        return      instructorRepo.findAllByIsActive(true).stream().map(instructor -> mapInstructorToDto(instructor)).collect(Collectors.toList());
    }

    @Override
    public Instructor findById(Long theId) {
        Instructor inst =null;
        Instructor res = instructorRepo.findByIdAndIsActive(theId,true);
        if(nonNull(res)){
            inst = res;
        }
        else {
            throw new RuntimeException("Did not find Instructor id -" + theId);

        }
        return inst;

    }

    @Override
    public void save(RegReqInstructorDto regReq) {
      instructorRepo.save(mapDtoToInstructor(regReq));
    }

    @Override
    public void deleteById(Long theId) {
        instructorRepo.deleteById(theId);

    }
}
