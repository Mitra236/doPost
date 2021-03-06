package com.example.postDo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.postDo.dto.FolderDTO;
import com.example.postDo.dto.MessageDTO;

import com.example.postDo.entity.Account;
import com.example.postDo.entity.Contact;
import com.example.postDo.entity.Folder;

import com.example.postDo.entity.Message;
import com.example.postDo.service.AccountServiceInterface;
import com.example.postDo.service.ContactServiceInterface;
import com.example.postDo.service.FolderServiceInterface;
import com.example.postDo.service.MessageServiceInterface;


@RestController
@RequestMapping(value = "api/messages")
public class MessageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageServiceInterface messageService;
	
	@Autowired
	private FolderServiceInterface folderService;
	
	@Autowired
	private AccountServiceInterface accountService;
	
	@Autowired
	private ContactServiceInterface contactService;
	
	@GetMapping
	public ResponseEntity<List<MessageDTO>> getMessages() {
		
		logger.info("Getting all messages");
		
		List<Message> messages = messageService.findAll();
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		for(Message m: messages) {
			messageDTO.add(new MessageDTO(m));
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Long id) {
		
		logger.info("Getting all messages by their id");
		
		Message message = messageService.findOne(id);
		
		if(message == null){
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
	
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<MessageDTO> updateMessage(@RequestBody MessageDTO messageDTO, @PathVariable("id") Long id) {
		
		logger.info("Method for updating messages, sets state of message from unread to read");
		
		Message message = messageService.findOne(id); 
		
		if (message == null) {
			return new ResponseEntity<MessageDTO>(HttpStatus.BAD_REQUEST);
		}
		
		message.setMessageRead(messageDTO.isMessageRead());
	
		message = messageService.save(message);
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);	
	}

	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) {
		
		logger.info("Deleted a message");
		
		Message message = messageService.findOne(id);
		
		if (message != null){
			messageService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} 
		else 
		{		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value = "/sortByDateAsc")
	public ResponseEntity<List<MessageDTO>> getSortByDateAsc() {
		
		logger.info("Sorted messages by date ascending");
		
		List<Message> messages = messageService.findAll();
		
		//Check if there are messages to sort
		if(messages == null) {
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		//Sort messages by date and time descending values
		messages.sort(Comparator.comparing(Message :: getDateTime));
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		for(Message m: messages) {
			messageDTO.add(new MessageDTO(m));
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/sortByDateDesc")
	public ResponseEntity<List<MessageDTO>> getSortByDateDesc() {
		
		logger.info("Sorted messages by date descending");
		
		List<Message> messages = messageService.findAll();
		
		//Check if there are messages to sort
		if(messages == null) {
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		//Sort messages by date and time ascending values
		messages.sort(Comparator.comparing(Message :: getDateTime).reversed());
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		for(Message m: messages) {
			messageDTO.add(new MessageDTO(m));
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/sortBySubjectAsc")
	public ResponseEntity<List<MessageDTO>> getSortBySubjectAsc() {
		
		logger.info("Sorted messages by subject ascending");
		
		List<Message> messages = messageService.findAll();
		
		//Check if there are messages to sort
		if(messages == null) {
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		//Sort messages by subject ascending values
		messages.sort(Comparator.comparing(Message :: getSubject));
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		for(Message m: messages) {
			messageDTO.add(new MessageDTO(m));
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/sortBySubjectDesc")
	public ResponseEntity<List<MessageDTO>> getSortBySubjectDesc() {
		
		logger.info("Sorted messages by date descending");
		
		List<Message> messages = messageService.findAll();
		
		//Check if there are messages to sort
		if(messages == null) {
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		//Sort messages by subject descending values
		messages.sort(Comparator.comparing(Message :: getSubject).reversed());
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		for(Message m: messages) {
			messageDTO.add(new MessageDTO(m));
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);

	}
	
	@GetMapping(value = "/filterList/{constraint}")
	private ResponseEntity<List<MessageDTO>> filterMessages(@PathVariable("constraint") String constraint){
		
		logger.info("Filtering messages by sender, subject and content");
		
		List<Message> messages = messageService.findAll();
		
		List<MessageDTO> messageDTO = new ArrayList<MessageDTO>();
		
		String filteredPattern = constraint.toString().toLowerCase().trim();
		
		for(Message m: messages) {
			if(m.getFrom().getFirstName().toString().toLowerCase().trim().contains(filteredPattern) 
					|| m.getSubject().toLowerCase().trim().contains(filteredPattern) 
						|| m.getContent().toLowerCase().trim().contains(filteredPattern)) {
						messageDTO.add(new MessageDTO(m));
			}
		}
		
		return new ResponseEntity<List<MessageDTO>>(messageDTO, HttpStatus.OK);
	}
	
	
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDTO) {
		
		Message message = new Message();
		
		message.setFrom(contactService.findOne(messageDTO.getFrom().getId()));
		message.setAccount(accountService.findOne(messageDTO.getAccount().getId()));
		message.setSubject(messageDTO.getSubject());
		message.setDateTime(messageDTO.getDateTime());
		message.setContent(messageDTO.getContent());
		message.setFolder(folderService.findOne(messageDTO.getFolder().getId()));
	
		message = messageService.save(message);

		
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.CREATED);	
	}
	
	@PostMapping(value="/findFolderMessages/{accId}")
	public ResponseEntity<List<MessageDTO>> findFolderMessages(@RequestBody FolderDTO dir, @PathVariable("accId") Long id) {
		ArrayList<MessageDTO> msgs = new ArrayList<>();
		
		Folder parent = folderService.findOne(dir.getId());
		
		FolderDTO fdto = new FolderDTO(parent);
		
		for(Account acc : accountService.findAll()) {
			if(acc.getMessages() != null) {
				for(Message messy : acc.getMessages()) {
					if(messy.getFolder() != null) {
						if(messy.getFolder().getId() == dir.getId()) {
							msgs.add(new MessageDTO(messy));
							fdto.addMessage(new MessageDTO(messy));
							System.out.println("Added msg: " + messy.getFrom().getFirstName());
						}
					}
					
				}
			}
		}
		
		
		return new ResponseEntity<List<MessageDTO>>(fdto.getMessages(), HttpStatus.OK);
		
	}
	
	@PostMapping(value="/setFolderMessage/{accId}/{folderId}")
	public ResponseEntity<MessageDTO> setFolderMessage(@RequestBody MessageDTO msg, @PathVariable("accId") Long id, @PathVariable("folderId") Long fid) {
		System.out.println("setFolder called");
		
		Folder dir = folderService.findOne(fid);
		Message messy = messageService.findOne(msg.getId());
		
		dir.addMessage(messy);
		messy.setFolder(dir);
		messageService.save(messy);
		
		System.out.println(messy.getSubject() + " added to folder " + dir.getName());
		

		System.out.println("Setting completed");
		return new ResponseEntity<MessageDTO>(msg, HttpStatus.OK);
		
	}
	
	
	
}
