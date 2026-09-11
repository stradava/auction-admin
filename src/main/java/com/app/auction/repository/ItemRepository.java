package com.app.auction.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;

public interface ItemRepository extends JpaRepository<AuctionItem, Long> {

	@Query(value = """
			select
				id,
				"name",
				description,
				starting_price,
				current_highest_bid,
				end_time,
				created_at,
				updated_at,
				status
			from tb_item ti
			where id = :id
			for update
			            """, nativeQuery = true)
	Optional<AuctionItem> findByIdForUpdate(@Param("id") Long id);

	@Query(value = """
			select
				id,
				"name",
				description,
				starting_price,
				current_highest_bid,
				end_time,
				created_at,
				updated_at,
				status
			from tb_item ti
			where status = :#{#status?.name()}
			            """, nativeQuery = true)
	List<AuctionItem> findAllByStatus(@Param("status") AuctionStatus status);

	@Query(value = """
			with incrementtable as (
				select
					item_id, mode() within group (order by "increment") as bid_increment_mode
				from (
					select
						tb.item_id,
						id,
						tb.amount,
						tb.amount - coalesce(lag(tb.amount) over (partition by item_id order by id asc), tb.amount) as "increment"
					from tb_bid tb
					order by id asc
				) sub
				group by item_id
			)
			select
				ti.id,
				ti."name",
				ti.description,
				ti.starting_price,
				ti.current_highest_bid,
				ti.end_time,
				ti.created_at,
				ti.updated_at,
				ti.status,
				MIN(tb.created_at) as first_bid,
				MAX(tb.created_at) as final_bid,
				COUNT(tb.id) as bid_count,
				MIN(tb.amount) as min_bid,
				MAX(tb.amount) as max_bid,
				inctb.bid_increment_mode
			from tb_item ti
			left join tb_bid tb on ti.id = tb.item_id
			left join incrementtable inctb on ti.id = inctb.item_id
			where ti.id = :id
			group by ti.id,
				ti."name",
				ti.description,
				ti.starting_price,
				ti.current_highest_bid,
				ti.end_time,
				ti.created_at,
				ti.updated_at,
				ti.status,
				inctb.bid_increment_mode
						""", nativeQuery = true)
	Optional<Map<String, Object>> findSummaryById(@Param("id") Long id);

}
