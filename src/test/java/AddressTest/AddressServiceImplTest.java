package AddressTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kadirkaganyuksel.dto.address.DtoAddress;
import com.kadirkaganyuksel.dto.address.DtoAddressIU;
import com.kadirkaganyuksel.model.Address;
import com.kadirkaganyuksel.repository.AddressRepository;
import com.kadirkaganyuksel.service.impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

	@Mock
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressServiceImpl addressService;
	
	@Test
	public void testSavedAddress() {
		DtoAddressIU dtoInput = new DtoAddressIU();
		dtoInput.setCity("Istanbul");
		dtoInput.setDistrict("Beşiktaş");
		dtoInput.setNeigbordhood("Moda");
		dtoInput.setStreet("Beyazıt");
		
		Address savedAddress = new Address();
		
		savedAddress.setId(1L);;
		savedAddress.setCity(dtoInput.getCity());
		savedAddress.setDistrict(dtoInput.getDistrict());
		savedAddress.setNeigbordhood(dtoInput.getNeigbordhood());
		savedAddress.setStreet(dtoInput.getStreet());
		savedAddress.setCreateTime(new Date());
		
		when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);
		
		DtoAddress result = addressService.saveAddress(dtoInput);
		
		assertNotNull(result);
		assertEquals(savedAddress.getId(), result.getId());
		assertEquals("Istanbul", result.getCity());
		verify(addressRepository, times(1)).save(any(Address.class));
		System.out.println("sistem testi geçti " + result);
	}
	
	@Test
	public void testGetByAddressIdFound() {
		Long id = 1L;
		
		Address address = new Address();
		address.setId(id);
		address.setCity("Ankara");
		address.setDistrict("Çankaya");
		address.setNeigbordhood("Kızılay");
		address.setStreet("Atatürk blv");
		
		when(addressRepository.findById(id)).thenReturn(Optional.of(address));
		
		DtoAddress dtoAddress = addressService.getbyAddressId(id);
		
		assertNotNull(dtoAddress);
		assertEquals("Ankara", dtoAddress.getCity());
		System.out.println("Test Başarılı " + dtoAddress);
		
	}
	
	@Test
	public void testGetByAddressIdNotFound() {
		Long id = 2L;
		when(addressRepository.findById(id)).thenReturn(Optional.empty());
		DtoAddress dtoAddress = addressService.getbyAddressId(id);
		
		assertNull(dtoAddress);
		System.out.println(dtoAddress);
	}
	
	@Test
	public void testDeleteAddress() {
		Long id = 1L;
		Address address = new Address();
		
		when(addressRepository.findById(id)).thenReturn(Optional.of(address));
		
		addressService.deleteAddress(id);
		verify(addressRepository,times(1)).delete(address);
		System.out.println("Silme başarılıdır." + address);
		
	}
	
	@Test
	public void testUpdateAddress_Found() {
	    long id = 1L;
	    DtoAddressIU dtoInput = new DtoAddressIU();
	    dtoInput.setCity("Istanbul");
	    dtoInput.setDistrict("Kadikoy");
	    dtoInput.setNeigbordhood("Moda");
	    dtoInput.setStreet("Example Street");

	    Address existingAddress = new Address();
	    existingAddress.setId(id);
	    existingAddress.setCity("Old City");
	    existingAddress.setDistrict("Old District");
	    existingAddress.setNeigbordhood("Old Neighborhood");
	    existingAddress.setStreet("Old Street");

	    when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));
	    when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

	    DtoAddress result = addressService.updateAddress(id, dtoInput);

	    assertNotNull(result);
	    assertEquals("Istanbul", result.getCity());
	    assertEquals("Kadikoy", result.getDistrict());
	    verify(addressRepository).save(existingAddress);
	    
	    System.out.println("Update başarılı." + result);
	}

}
