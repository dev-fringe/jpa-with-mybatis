package dev.fringe.repositories

import org.apache.ibatis.annotations.Select
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import dev.fringe.entity.Person

/**
 * @author hdlee
 * enable combine?
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String>{
}
interface PersonMapper{
	@Select("""<script>
			SELECT
				id
				, birth_date
				, location
				, NAME 
			FROM
				person
		</script>""")
	public List<Person> select();
}