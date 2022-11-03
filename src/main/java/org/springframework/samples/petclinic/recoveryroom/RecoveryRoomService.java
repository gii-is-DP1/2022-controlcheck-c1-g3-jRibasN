package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoveryRoomService {
    private RecoveryRoomRepository recoveryRoomRepository;

    @Autowired(required = true)
	public RecoveryRoomService(RecoveryRoomRepository RecoveryRoomRepository) {
		this.recoveryRoomRepository = RecoveryRoomRepository;
	}

    public List<RecoveryRoom> getAll(){
        return recoveryRoomRepository.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return recoveryRoomRepository.findAllRecoveryRoomTypes();
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return recoveryRoomRepository.findRecoveryRoomTypeByName(typeName);
    }

    @Transactional(rollbackOn = DuplicatedRoomNameException.class)
    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
    	RecoveryRoom room = recoveryRoomRepository.findByName(p.getName());
    	if (room == null) {
    		return recoveryRoomRepository.save(p);   
    	} else {
    		throw new DuplicatedRoomNameException();
    	}
    }

    
}
